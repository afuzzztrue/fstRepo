package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.common.BusinessException;
import com.sunmao.ljx.entity.User;
import com.sunmao.ljx.mapper.UserMapper;
import com.sunmao.ljx.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 用户服务实现类
 * 功能说明: 实现UserService接口中定义的所有用户业务逻辑
 * 注解说明:
 *   @Service - 标识为Spring的服务层组件，由Spring容器管理生命周期
 *   @Transactional - 声明事务边界，确保数据库操作的原子性
 * 继承说明: 继承ServiceImpl<UserMapper, User>获得MyBatis-Plus的基础CRUD实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据微信openid查询用户
     * 实现逻辑: 直接调用UserMapper的selectByOpenid方法
     *
     * @param openid 微信openid
     * @return 用户对象或null
     */
    @Override
    public User getByOpenid(String openid) {
        return baseMapper.selectByOpenid(openid);
    }

    /**
     * 根据手机号查询用户
     * 实现逻辑: 直接调用UserMapper的selectByPhone方法
     *
     * @param phone 手机号
     * @return 用户对象或null
     */
    @Override
    public User getByPhone(String phone) {
        return baseMapper.selectByPhone(phone);
    }

    /**
     * 根据邮箱查询用户
     * 实现逻辑: 直接调用UserMapper的selectByEmail方法
     *
     * @param email 邮箱
     * @return 用户对象或null
     */
    @Override
    public User getByEmail(String email) {
        return baseMapper.selectByEmail(email);
    }

    /**
     * 微信小程序登录实现
     * 业务逻辑详解:
     *   1. 先根据openid查询用户是否存在
     *   2. 如果不存在（首次登录），创建新用户:
     *      - 设置openid、unionid、nickname、avatar
     *      - 初始化studyHours为0，userType为0（普通用户），status为1（正常）
     *      - 设置当前时间为创建时间和更新时间
     *      - 调用save()保存到数据库
     *   3. 如果存在（非首次登录），更新用户信息:
     *      - 更新nickname和avatar（微信信息可能变化）
     *      - 更新updateTime为当前时间
     *      - 调用updateById()更新数据库
     * 事务说明: @Transactional确保整个登录流程要么全部成功，要么全部回滚
     *
     * @param openid 微信openid
     * @param unionid 微信unionid
     * @param nickname 微信昵称
     * @param avatar 微信头像
     * @return 登录成功的用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User wxLogin(String openid, String unionid, String nickname, String avatar) {
        User user = getByOpenid(openid);
        if (user == null) {
            // 首次登录，创建新用户
            user = new User();
            user.setOpenid(openid);
            user.setUnionid(unionid);
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setStudyHours(0);
            user.setUserType(0);
            user.setStatus(1);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            save(user);
        } else {
            // 非首次登录，更新用户信息
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setUpdateTime(LocalDateTime.now());
            updateById(user);
        }
        return user;
    }

    /**
     * 用户注册实现
     * 业务逻辑详解:
     *   1. 唯一性校验:
     *      - 如果填写了手机号，检查手机号是否已被注册
     *      - 如果填写了邮箱，检查邮箱是否已被注册
     *      - 已存在则抛出BusinessException业务异常
     *   2. 密码加密:
     *      - 调用encryptPassword()方法对明文密码进行MD5加密
     *   3. 创建用户:
     *      - 设置phone、email、加密后的password、nickname
     *      - 初始化studyHours为0，userType为0，status为1
     *      - 设置当前时间为创建时间和更新时间
     *      - 调用save()保存到数据库
     * 事务说明: @Transactional确保注册流程的原子性
     *
     * @param phone 手机号
     * @param email 邮箱
     * @param password 明文密码
     * @param nickname 昵称
     * @return 注册成功的用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone, String email, String password, String nickname) {
        // 校验手机号是否已注册
        if (StringUtils.hasText(phone) && getByPhone(phone) != null) {
            throw new BusinessException("手机号已注册");
        }
        // 校验邮箱是否已注册
        if (StringUtils.hasText(email) && getByEmail(email) != null) {
            throw new BusinessException("邮箱已注册");
        }

        // 创建新用户
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(encryptPassword(password));
        user.setNickname(nickname);
        user.setStudyHours(0);
        user.setUserType(0);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        save(user);
        return user;
    }

    /**
     * 用户登录实现
     * 业务逻辑详解:
     *   1. 判断账号类型:
     *      - 包含"@"符号认为是邮箱，调用getByEmail查询
     *      - 否则认为是手机号，调用getByPhone查询
     *   2. 用户存在性校验:
     *      - 用户不存在则抛出"用户不存在"异常
     *   3. 密码校验:
     *      - 对输入密码进行MD5加密
     *      - 与数据库中存储的加密密码比对
     *      - 不一致则抛出"密码错误"异常
     *   4. 状态校验:
     *      - 用户状态为0（禁用）则抛出"账号已被禁用"异常
     *   5. 返回用户对象
     *
     * @param account 登录账号（手机号或邮箱）
     * @param password 明文密码
     * @return 登录成功的用户对象
     */
    @Override
    public User login(String account, String password) {
        User user = null;
        // 根据账号类型选择查询方式
        if (account.contains("@")) {
            user = getByEmail(account);
        } else {
            user = getByPhone(account);
        }
        // 校验用户是否存在
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        // 校验密码
        if (!encryptPassword(password).equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        // 校验账号状态
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }
        return user;
    }

    /**
     * 更新用户学习时长实现
     * 业务逻辑详解:
     *   1. 根据userId查询用户
     *   2. 如果用户存在，累加学习时长:
     *      - 新时长 = 原时长 + 新增时长
     *      - 更新updateTime为当前时间
     *      - 调用updateById()更新数据库
     * 事务说明: @Transactional确保更新操作的原子性
     *
     * @param userId 用户ID
     * @param hours 新增学习时长
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudyHours(Integer userId, Integer hours) {
        User user = getById(userId);
        if (user != null) {
            user.setStudyHours(user.getStudyHours() + hours);
            user.setUpdateTime(LocalDateTime.now());
            updateById(user);
        }
    }

    /**
     * 密码加密方法（私有工具方法）
     * 加密算法: MD5哈希算法
     * 使用场景: 用户注册时对密码加密存储，登录时对输入密码加密后比对
     * 安全说明: MD5是一种单向哈希算法，无法从密文反推出明文密码
     *
     * @param password 明文密码
     * @return MD5加密后的密码字符串
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
