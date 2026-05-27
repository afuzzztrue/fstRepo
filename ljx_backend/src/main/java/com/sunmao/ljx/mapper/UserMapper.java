package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE openid = #{openid} LIMIT 1")
    User selectByOpenid(@Param("openid") String openid);

    @Select("SELECT * FROM user WHERE phone = #{phone} LIMIT 1")
    User selectByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
    User selectByEmail(@Param("email") String email);
}
