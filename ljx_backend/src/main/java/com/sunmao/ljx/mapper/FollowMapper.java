package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 关注关系数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Follow实体的基础CRUD操作
 * 扩展方法: 提供查询关注关系、统计关注数和粉丝数的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    /**
     * 根据用户ID和被关注用户ID查询关注关系
     * 使用场景: 判断用户A是否已关注用户B，用于关注/取消关注的切换逻辑
     * SQL说明: 查询follow表中user_id和follow_user_id同时匹配的记录，LIMIT 1确保只返回一条
     *
     * @param userId 关注者用户ID
     * @param followUserId 被关注用户ID
     * @return 匹配的关注关系对象，不存在则返回null（表示未关注）
     */
    @Select("SELECT * FROM follow WHERE user_id = #{userId} AND follow_user_id = #{followUserId} LIMIT 1")
    Follow selectByUserAndTarget(@Param("userId") Integer userId, @Param("followUserId") Integer followUserId);

    /**
     * 统计用户的关注数量
     * 使用场景: 个人主页展示"关注"数量
     * SQL说明: 使用COUNT(*)统计follow表中user_id匹配的记录数
     *
     * @param userId 用户ID
     * @return 该用户关注的其他用户数量
     */
    @Select("SELECT COUNT(*) FROM follow WHERE user_id = #{userId}")
    Integer selectFollowCount(@Param("userId") Integer userId);

    /**
     * 统计用户的粉丝数量
     * 使用场景: 个人主页展示"粉丝"数量
     * SQL说明: 使用COUNT(*)统计follow表中follow_user_id匹配的记录数
     *
     * @param userId 用户ID
     * @return 关注该用户的其他用户数量（粉丝数）
     */
    @Select("SELECT COUNT(*) FROM follow WHERE follow_user_id = #{userId}")
    Integer selectFollowerCount(@Param("userId") Integer userId);
}
