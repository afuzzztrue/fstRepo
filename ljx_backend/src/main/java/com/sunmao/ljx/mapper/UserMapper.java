package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供User实体的基础CRUD操作
 * 扩展方法: 提供根据openid、phone、email查询用户的自定义SQL方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口，由Spring扫描并生成代理对象
 *   @Select - 使用注解方式编写SQL查询语句，替代XML映射文件
 *   @Param - 为SQL参数命名，解决JDK编译后参数名丢失的问题
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据微信openid查询用户
     * 使用场景: 微信小程序用户登录时，通过openid识别用户身份
     * SQL说明: 查询user表中openid匹配的记录，LIMIT 1确保只返回一条
     *
     * @param openid 微信用户的唯一标识openid
     * @return 匹配的用户对象，不存在则返回null
     */
    @Select("SELECT * FROM user WHERE openid = #{openid} LIMIT 1")
    User selectByOpenid(@Param("openid") String openid);

    /**
     * 根据手机号查询用户
     * 使用场景: 用户通过手机号+密码登录时验证用户是否存在
     * SQL说明: 查询user表中phone匹配的记录，LIMIT 1确保只返回一条
     *
     * @param phone 用户注册时填写的手机号码
     * @return 匹配的用户对象，不存在则返回null
     */
    @Select("SELECT * FROM user WHERE phone = #{phone} LIMIT 1")
    User selectByPhone(@Param("phone") String phone);

    /**
     * 根据邮箱查询用户
     * 使用场景: 用户通过邮箱+密码登录时验证用户是否存在
     * SQL说明: 查询user表中email匹配的记录，LIMIT 1确保只返回一条
     *
     * @param email 用户注册时填写的电子邮箱
     * @return 匹配的用户对象，不存在则返回null
     */
    @Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
    User selectByEmail(@Param("email") String email);
}
