package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.User;

/**
 * 用户服务接口
 * 功能说明: 定义用户模块的业务逻辑接口，继承MyBatis-Plus的IService获取基础CRUD能力
 * 设计模式: 采用接口+实现类的分层设计，便于解耦和单元测试
 * 接口职责: 声明用户相关的所有业务操作方法，由UserServiceImpl提供具体实现
 */
public interface UserService extends IService<User> {

    /**
     * 根据微信openid查询用户
     * 使用场景: 微信小程序用户登录时，通过openid识别用户身份
     *
     * @param openid 微信用户的唯一标识openid
     * @return 匹配的用户对象，不存在则返回null
     */
    User getByOpenid(String openid);

    /**
     * 根据手机号查询用户
     * 使用场景: 用户通过手机号+密码登录时验证用户是否存在
     *
     * @param phone 用户注册时填写的手机号码
     * @return 匹配的用户对象，不存在则返回null
     */
    User getByPhone(String phone);

    /**
     * 根据邮箱查询用户
     * 使用场景: 用户通过邮箱+密码登录时验证用户是否存在
     *
     * @param email 用户注册时填写的电子邮箱
     * @return 匹配的用户对象，不存在则返回null
     */
    User getByEmail(String email);

    /**
     * 微信小程序登录
     * 使用场景: 用户通过微信小程序授权登录
     * 业务逻辑:
     *   1. 根据openid查询用户是否存在
     *   2. 不存在则自动注册新用户
     *   3. 存在则更新用户昵称和头像信息
     * 事务控制: 涉及数据库写入操作，需要事务保证数据一致性
     *
     * @param openid 微信openid
     * @param unionid 微信unionid（可能为空）
     * @param nickname 微信昵称
     * @param avatar 微信头像URL
     * @return 登录成功的用户对象
     */
    User wxLogin(String openid, String unionid, String nickname, String avatar);

    /**
     * 用户注册
     * 使用场景: 用户通过手机号或邮箱注册账号
     * 业务逻辑:
     *   1. 校验手机号/邮箱是否已被注册
     *   2. 对密码进行加密处理
     *   3. 创建新用户记录
     * 事务控制: 涉及数据库写入操作，需要事务保证数据一致性
     *
     * @param phone 手机号（与email至少填一个）
     * @param email 邮箱（与phone至少填一个）
     * @param password 明文密码（会被加密存储）
     * @param nickname 用户昵称
     * @return 注册成功的用户对象
     */
    User register(String phone, String email, String password, String nickname);

    /**
     * 用户登录
     * 使用场景: 用户通过手机号/邮箱+密码登录
     * 业务逻辑:
     *   1. 根据账号（手机号或邮箱）查询用户
     *   2. 校验密码是否正确
     *   3. 校验账号状态是否正常
     *
     * @param account 登录账号（手机号或邮箱）
     * @param password 明文密码
     * @return 登录成功的用户对象
     */
    User login(String account, String password);

    /**
     * 更新用户学习时长
     * 使用场景: 用户观看课程或阅读文章后，累计学习时长
     * 业务逻辑: 在原有学习时长基础上累加新的学习时长
     * 事务控制: 涉及数据库更新操作，需要事务保证数据一致性
     *
     * @param userId 用户ID
     * @param hours 新增的学习时长（小时）
     */
    void updateStudyHours(Integer userId, Integer hours);
}
