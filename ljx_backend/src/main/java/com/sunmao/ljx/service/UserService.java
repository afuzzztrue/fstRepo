package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.User;

/**
 * 用户服务接口
 * 作用：定义用户相关的业务逻辑操作
 *
 * 为什么需要 Service 接口？
 * 1. 面向接口编程：Controller 层依赖接口而非实现类，降低耦合度
 * 2. 便于扩展：可以方便地切换不同的实现（如从 MySQL 切换到 MongoDB）
 * 3. 便于测试：可以使用 Mock 对象进行单元测试
 * 4. 符合 Spring 的 AOP 编程思想
 *
 * 继承 IService<User> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 */
public interface UserService extends IService<User> {

    /**
     * 根据微信 openid 查询用户
     * 用于微信登录时查找已存在的用户
     *
     * @param openid 微信用户的 openid
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    User getByOpenid(String openid);

    /**
     * 根据手机号查询用户
     * 用于账号密码登录和注册时校验手机号是否已存在
     *
     * @param phone 用户手机号
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    User getByPhone(String phone);

    /**
     * 根据邮箱查询用户
     * 用于账号密码登录和注册时校验邮箱是否已存在
     *
     * @param email 用户邮箱
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    User getByEmail(String email);

    /**
     * 微信登录
     * 根据微信 openid 进行登录，如果不存在则自动注册
     *
     * @param openid   微信用户的 openid
     * @param unionid  微信用户的 unionid（可选）
     * @param nickname 用户昵称（可选）
     * @param avatar   用户头像（可选）
     * @return 登录成功的用户对象
     */
    User wxLogin(String openid, String unionid, String nickname, String avatar);

    /**
     * 用户注册
     * 使用手机号或邮箱进行注册
     *
     * @param phone    手机号（与 email 二选一）
     * @param email    邮箱（与 phone 二选一）
     * @param password 密码
     * @param nickname 昵称
     * @return 注册成功的用户对象
     */
    User register(String phone, String email, String password, String nickname);

    /**
     * 用户登录
     * 使用手机号或邮箱进行登录
     *
     * @param account  账号（手机号或邮箱）
     * @param password 密码
     * @return 登录成功的用户对象
     */
    User login(String account, String password);

    /**
     * 更新学习时长
     * 用于记录用户的学习进度
     *
     * @param userId 用户ID
     * @param hours  新增的学习时长（小时）
     */
    void updateStudyHours(Integer userId, Integer hours);
}
