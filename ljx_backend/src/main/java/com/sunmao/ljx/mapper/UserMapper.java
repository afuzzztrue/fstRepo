package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层（Mapper）
 * 作用：定义对用户表（user）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<User> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 * - 可以明确表明此接口的用途
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据微信 openid 查询用户
     * 用于微信登录时查找已存在的用户
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     * - 复杂查询建议使用 XML 配置
     *
     * SQL 说明：
     * - SELECT * FROM user：查询 user 表的所有字段
     * - WHERE openid = #{openid}：条件筛选，openid 等于传入的参数
     * - LIMIT 1：只返回一条记录（openid 是唯一的）
     *
     * @Param("openid") 注解说明：
     * - 指定参数在 SQL 中的名称
     * - 当方法有多个参数时，必须用 @Param 指定名称
     * - 单个参数时可以省略，但建议保留以增强可读性
     *
     * @param openid 微信用户的 openid
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM user WHERE openid = #{openid} LIMIT 1")
    User selectByOpenid(@Param("openid") String openid);

    /**
     * 根据手机号查询用户
     * 用于账号密码登录和注册时校验手机号是否已存在
     *
     * SQL 说明：
     * - WHERE phone = #{phone}：条件筛选，phone 等于传入的参数
     * - LIMIT 1：只返回一条记录（手机号是唯一的）
     *
     * @param phone 用户手机号
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM user WHERE phone = #{phone} LIMIT 1")
    User selectByPhone(@Param("phone") String phone);

    /**
     * 根据邮箱查询用户
     * 用于账号密码登录和注册时校验邮箱是否已存在
     *
     * SQL 说明：
     * - WHERE email = #{email}：条件筛选，email 等于传入的参数
     * - LIMIT 1：只返回一条记录（邮箱是唯一的）
     *
     * @param email 用户邮箱
     * @return 匹配的用户对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
    User selectByEmail(@Param("email") String email);
}
