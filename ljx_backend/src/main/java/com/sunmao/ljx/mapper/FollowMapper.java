package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 关注关系数据访问层（Mapper）
 * 作用：定义对关注关系表（follow）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Follow> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

    /**
     * 查询两个用户之间的关注关系
     * 用于判断用户 A 是否关注了用户 B
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM follow：查询 follow 表的所有字段
     * - WHERE user_id = #{userId}：筛选关注者
     * - AND follow_user_id = #{followUserId}：筛选被关注者
     * - LIMIT 1：只返回一条记录（关注关系是唯一的）
     *
     * @param userId       关注者用户ID
     * @param followUserId 被关注者用户ID
     * @return 关注关系对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM follow WHERE user_id = #{userId} AND follow_user_id = #{followUserId} LIMIT 1")
    Follow selectByUserAndTarget(@Param("userId") Integer userId, @Param("followUserId") Integer followUserId);

    /**
     * 查询用户的关注数量
     * 用于统计用户关注了多少人
     *
     * SQL 说明：
     * - SELECT COUNT(*)：统计记录数
     * - FROM follow：从 follow 表查询
     * - WHERE user_id = #{userId}：筛选该用户的关注记录
     *
     * @param userId 用户ID
     * @return 关注数量
     */
    @Select("SELECT COUNT(*) FROM follow WHERE user_id = #{userId}")
    Integer selectFollowCount(@Param("userId") Integer userId);

    /**
     * 查询用户的粉丝数量
     * 用于统计有多少人关注了该用户
     *
     * SQL 说明：
     * - SELECT COUNT(*)：统计记录数
     * - FROM follow：从 follow 表查询
     * - WHERE follow_user_id = #{userId}：筛选关注该用户的记录
     *
     * @param userId 用户ID
     * @return 粉丝数量
     */
    @Select("SELECT COUNT(*) FROM follow WHERE follow_user_id = #{userId}")
    Integer selectFollowerCount(@Param("userId") Integer userId);
}
