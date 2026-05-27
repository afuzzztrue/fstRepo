package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Footprint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 浏览足迹数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Footprint实体的基础CRUD操作
 * 扩展方法: 提供查询用户最近浏览记录的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface FootprintMapper extends BaseMapper<Footprint> {

    /**
     * 查询用户最近的浏览记录
     * 使用场景: "我的足迹"页面展示用户最近浏览过的文章
     * SQL说明: 查询footprint表中user_id匹配的记录，按create_time降序排列（最新的在前），LIMIT限制返回条数
     *
     * @param userId 用户ID
     * @param limit 限制返回的记录数量，如传入20表示只返回最近20条
     * @return 用户的浏览足迹列表，按时间倒序排列
     */
    @Select("SELECT * FROM footprint WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT #{limit}")
    List<Footprint> selectRecentByUserId(@Param("userId") Integer userId, @Param("limit") Integer limit);
}
