package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 动态帖子数据访问层（Mapper）
 * 作用：定义对帖子表（post）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Post> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 分页查询帖子列表
     * 用于动态广场展示帖子列表
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM post：查询 post 表的所有字段
     * - WHERE status = 1：筛选正常的帖子
     * - ORDER BY create_time DESC：按创建时间降序排列（最新的在前面）
     * - LIMIT #{offset}, #{limit}：分页查询
     *   - offset：跳过的记录数（(页码-1) * 每页大小）
     *   - limit：返回的记录数
     *
     * @param offset 跳过的记录数
     * @param limit  返回的记录数
     * @return 帖子列表
     */
    @Select("SELECT * FROM post WHERE status = 1 ORDER BY create_time DESC LIMIT #{offset}, #{limit}")
    List<Post> selectPageList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据用户ID查询帖子列表
     * 用于用户主页展示该用户发布的帖子
     *
     * SQL 说明：
     * - WHERE user_id = #{userId}：筛选指定用户的帖子
     * - AND status = 1：筛选正常的帖子
     * - ORDER BY create_time DESC：按创建时间降序排列
     *
     * @param userId 用户ID
     * @return 该用户的帖子列表
     */
    @Select("SELECT * FROM post WHERE user_id = #{userId} AND status = 1 ORDER BY create_time DESC")
    List<Post> selectByUserId(@Param("userId") Integer userId);

    /**
     * 更新帖子点赞次数
     * 用于点赞和取消点赞时更新点赞数
     *
     * @Update 注解说明：
     * - 标识这是一个更新操作的 SQL
     * - 执行后会返回受影响的行数
     *
     * SQL 说明：
     * - UPDATE post：更新 post 表
     * - SET like_count = like_count + #{delta}：将点赞次数增加或减少
     * - WHERE post_id = #{postId}：指定要更新的帖子
     *
     * @param postId 帖子ID
     * @param delta  变化量（+1 或 -1）
     * @return 受影响的行数
     */
    @Update("UPDATE post SET like_count = like_count + #{delta} WHERE post_id = #{postId}")
    int updateLikeCount(@Param("postId") Integer postId, @Param("delta") Integer delta);

    /**
     * 更新帖子评论次数
     * 用于发表评论和删除评论时更新评论数
     *
     * SQL 说明：
     * - SET comment_count = comment_count + #{delta}：将评论次数增加或减少
     * - delta 为正数表示新增评论，为负数表示删除评论
     *
     * @param postId 帖子ID
     * @param delta  变化量（+1 或 -1）
     * @return 受影响的行数
     */
    @Update("UPDATE post SET comment_count = comment_count + #{delta} WHERE post_id = #{postId}")
    int updateCommentCount(@Param("postId") Integer postId, @Param("delta") Integer delta);
}
