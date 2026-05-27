package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 动态帖子数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Post实体的基础CRUD操作
 * 扩展方法: 提供分页查询帖子、查询用户帖子、更新点赞数/评论数等操作
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Update - 使用注解方式编写SQL更新语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 分页查询帖子列表
     * 使用场景: 动态广场页面加载帖子列表，支持分页展示
     * SQL说明: 查询status=1（正常状态）的帖子，按create_time降序排列，使用LIMIT实现分页
     * 分页计算: offset = (page - 1) * size，表示跳过前面多少条记录
     *
     * @param offset 跳过的记录数，计算公式为(page-1)*size
     * @param limit 每页返回的记录数
     * @return 帖子列表
     */
    @Select("SELECT * FROM post WHERE status = 1 ORDER BY create_time DESC LIMIT #{offset}, #{limit}")
    List<Post> selectPageList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据用户ID查询帖子列表
     * 使用场景: 用户个人主页展示该用户发布的所有帖子
     * SQL说明: 查询user_id匹配且status=1的帖子，按create_time降序排列
     *
     * @param userId 用户ID
     * @return 该用户发布的帖子列表
     */
    @Select("SELECT * FROM post WHERE user_id = #{userId} AND status = 1 ORDER BY create_time DESC")
    List<Post> selectByUserId(@Param("userId") Integer userId);

    /**
     * 更新帖子点赞数
     * 使用场景: 用户点赞或取消点赞帖子时，更新点赞数量
     * SQL说明: 使用UPDATE语句将like_count字段增加或减少指定数值
     * 参数说明: delta为正数表示点赞（+1），为负数表示取消点赞（-1）
     *
     * @param postId 帖子ID
     * @param delta 点赞数变化量，+1或-1
     * @return 更新的行数
     */
    @Update("UPDATE post SET like_count = like_count + #{delta} WHERE post_id = #{postId}")
    int updateLikeCount(@Param("postId") Integer postId, @Param("delta") Integer delta);

    /**
     * 更新帖子评论数
     * 使用场景: 用户发表评论或删除评论时，更新评论数量
     * SQL说明: 使用UPDATE语句将comment_count字段增加或减少指定数值
     * 参数说明: delta为正数表示新增评论（+1），为负数表示删除评论（-1）
     *
     * @param postId 帖子ID
     * @param delta 评论数变化量，+1或-1
     * @return 更新的行数
     */
    @Update("UPDATE post SET comment_count = comment_count + #{delta} WHERE post_id = #{postId}")
    int updateCommentCount(@Param("postId") Integer postId, @Param("delta") Integer delta);
}
