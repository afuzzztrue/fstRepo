package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Comment实体的基础CRUD操作
 * 扩展方法: 提供查询一级评论（根评论）和查询回复评论的方法
 * 设计说明: 通过parent_id实现评论的层级结构，parent_id=0表示一级评论，其他值表示回复
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询目标对象的一级评论（根评论）列表
     * 使用场景: 文章详情页或帖子详情页加载评论列表时，先加载一级评论
     * SQL说明: 查询target_type和target_id匹配、parent_id=0（一级评论）、status=1（正常状态）的评论，按create_time降序排列
     * 多态关联说明:
     *   target_type=1时表示查询文章的评论，target_id对应article表的article_id
     *   target_type=2时表示查询帖子的评论，target_id对应post表的post_id
     *
     * @param targetType 评论目标类型，1表示文章，2表示帖子
     * @param targetId 评论目标ID，对应被评论的文章ID或帖子ID
     * @return 一级评论列表，按时间倒序排列
     */
    @Select("SELECT * FROM comment WHERE target_type = #{targetType} AND target_id = #{targetId} AND parent_id = 0 AND status = 1 ORDER BY create_time DESC")
    List<Comment> selectRootComments(@Param("targetType") Integer targetType, @Param("targetId") Integer targetId);

    /**
     * 查询某条评论的回复列表
     * 使用场景: 用户点击"查看回复"时，加载该评论下的所有回复
     * SQL说明: 查询parent_id匹配且status=1（正常状态）的评论，按create_time升序排列（先回复的在前）
     *
     * @param parentId 父评论ID，即被回复的评论ID
     * @return 回复评论列表，按时间正序排列
     */
    @Select("SELECT * FROM comment WHERE parent_id = #{parentId} AND status = 1 ORDER BY create_time")
    List<Comment> selectReplies(@Param("parentId") Integer parentId);
}
