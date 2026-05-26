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
 * 作用：实现用户相关的业务逻辑
 *
 * Service 层是三层架构中的业务逻辑层，负责处理业务规则
 * 主要工作：
 * 1. 接收 Controller 传递的参数
 * 2. 调用 Mapper 层进行数据操作
 * 3. 处理业务逻辑（如校验、计算、转换等）
 * 4. 返回处理结果
 *
 * @Service 注解说明：
 * - 标识这是一个 Spring 的 Service 组件
 * - Spring 会自动扫描并创建该类的 Bean 实例
 * - 可以被 @Autowired 注入到 Controller 中
 *
 * 继承 ServiceImpl<UserMapper, User> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 UserService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据微信 openid 查询用户
     * 直接调用 Mapper 层的方法
     *
     * @param openid 微信用户的 openid
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Override
    public User getByOpenid(String openid) {
        // 调用 UserMapper 的自定义方法查询用户
        return baseMapper.selectByOpenid(openid);
    }

    /**
     * 根据手机号查询用户
     * 直接调用 Mapper 层的方法
     *
     * @param phone 用户手机号
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Override
    public User getByPhone(String phone) {
        // 调用 UserMapper 的自定义方法查询用户
        return baseMapper.selectByPhone(phone);
    }

    /**
     * 根据邮箱查询用户
     * 直接调用 Mapper 层的方法
     *
     * @param email 用户邮箱
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Override
    public User getByEmail(String email) {
        // 调用 UserMapper 的自定义方法查询用户
        return baseMapper.selectByEmail(email);
    }

    /**
     * 微信登录
     * 根据微信 openid 进行登录，如果不存在则自动注册
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     * - rollbackFor = Exception.class 表示对所有异常都进行回滚
     *
     * 业务逻辑：
     * 1. 根据 openid 查询用户
     * 2. 如果用户不存在，创建新用户（自动注册）
     * 3. 如果用户存在，更新用户信息（昵称、头像等）
     * 4. 返回用户对象
     *
     * @param openid   微信用户的 openid
     * @param unionid  微信用户的 unionid（可选）
     * @param nickname 用户昵称（可选）
     * @param avatar   用户头像（可选）
     * @return 登录成功的用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User wxLogin(String openid, String unionid, String nickname, String avatar) {
        // 1. 根据 openid 查询用户
        User user = getByOpenid(openid);

        if (user == null) {
            // 2. 用户不存在，创建新用户（自动注册）
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
            // 保存用户到数据库
            save(user);
        } else {
            // 3. 用户存在，更新用户信息
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setUpdateTime(LocalDateTime.now());
            // 更新用户信息到数据库
            updateById(user);
        }

        // 4. 返回用户对象
        return user;
    }

    /**
     * 用户注册
     * 使用手机号或邮箱进行注册
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     *
     * 业务逻辑：
     * 1. 校验手机号是否已注册
     * 2. 校验邮箱是否已注册
     * 3. 创建新用户
     * 4. 保存到数据库
     *
     * @param phone    手机号（与 email 二选一）
     * @param email    邮箱（与 phone 二选一）
     * @param password 密码
     * @param nickname 昵称
     * @return 注册成功的用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone, String email, String password, String nickname) {
        // 1. 校验手机号是否已注册
        if (StringUtils.hasText(phone) && getByPhone(phone) != null) {
            // 手机号已存在，抛出业务异常
            throw new BusinessException("手机号已注册");
        }

        // 2. 校验邮箱是否已注册
        if (StringUtils.hasText(email) && getByEmail(email) != null) {
            // 邮箱已存在，抛出业务异常
            throw new BusinessException("邮箱已注册");
        }

        // 3. 创建新用户
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        // 密码加密存储
        user.setPassword(encryptPassword(password));
        user.setNickname(nickname);
        user.setStudyHours(0);
        user.setUserType(0);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 4. 保存到数据库
        save(user);

        return user;
    }

    /**
     * 用户登录
     * 使用手机号或邮箱进行登录
     *
     * 业务逻辑：
     * 1. 判断账号类型（手机号或邮箱）
     * 2. 查询用户
     * 3. 校验用户是否存在
     * 4. 校验密码是否正确
     * 5. 校验账号状态
     * 6. 返回用户对象
     *
     * @param account  账号（手机号或邮箱）
     * @param password 密码
     * @return 登录成功的用户对象
     */
    @Override
    public User login(String account, String password) {
        User user = null;

        // 1. 判断账号类型
        if (account.contains("@")) {
            // 包含 @ 符号，认为是邮箱
            user = getByEmail(account);
        } else {
            // 不包含 @ 符号，认为是手机号
            user = getByPhone(account);
        }

        // 2. 校验用户是否存在
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 3. 校验密码是否正确
        if (!encryptPassword(password).equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 4. 校验账号状态
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }

        // 5. 返回用户对象
        return user;
    }

    /**
     * 更新学习时长
     * 用于记录用户的学习进度
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     *
     * @param userId 用户ID
     * @param hours  新增的学习时长（小时）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudyHours(Integer userId, Integer hours) {
        // 根据用户ID查询用户
        User user = getById(userId);
        if (user != null) {
            // 累加学习时长
            user.setStudyHours(user.getStudyHours() + hours);
            // 更新更新时间
            user.setUpdateTime(LocalDateTime.now());
            // 更新到数据库
            updateById(user);
        }
    }

    /**
     * 密码加密
     * 使用 MD5 算法对密码进行加密
     *
     * 为什么需要加密密码？
     * 1. 防止数据库泄露时密码被直接获取
     * 2. 即使管理员也无法查看用户的明文密码
     * 3. 符合安全规范
     *
     * MD5 的特点：
     * - 单向加密，无法逆向解密
     * - 相同的输入总是产生相同的输出
     * - 不同的输入几乎不可能产生相同的输出
     *
     * 注意：实际项目中建议使用更安全的加密算法，如 BCrypt
     *
     * @param password 明文密码
     * @return 加密后的密码
     */
    private String encryptPassword(String password) {
        // 使用 Spring 提供的 DigestUtils 进行 MD5 加密
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
