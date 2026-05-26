package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论数据访问层（Mapper）
 * 作用：定义对评论表（comment）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Comment> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询指定目标的一级评论列表
     * 用于文章详情页或帖子详情页展示评论
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM comment：查询 comment 表的所有字段
     * - WHERE target_type = #{targetType}：筛选指定目标类型（1：文章、2：帖子）
     * - AND target_id = #{targetId}：筛选指定目标ID
     * - AND parent_id = 0：只查询一级评论（parent_id = 0 表示没有父评论）
     * - AND status = 1：筛选正常的评论
     * - ORDER BY create_time DESC：按创建时间降序排列（最新的在前面）
     *
     * @param targetType 目标类型（1：文章、2：帖子）
     * @param targetId   目标ID
     * @return 一级评论列表
     */
    @Select("SELECT * FROM comment WHERE target_type = #{targetType} AND target_id = #{targetId} AND parent_id = 0 AND status = 1 ORDER BY create_time DESC")
    List<Comment> selectRootComments(@Param("targetType") Integer targetType, @Param("targetId") Integer targetId);

    /**
     * 查询指定评论的回复列表
     * 用于展示某条评论的所有回复
     *
     * SQL 说明：
     * - WHERE parent_id = #{parentId}：筛选指定父评论的回复
     * - AND status = 1：筛选正常的评论
     * - ORDER BY create_time：按创建时间升序排列（最早的回复在前面）
     *
     * @param parentId 父评论ID
     * @return 回复列表
     */
    @Select("SELECT * FROM comment WHERE parent_id = #{parentId} AND status = 1 ORDER BY create_time")
    List<Comment> selectReplies(@Param("parentId") Integer parentId);
}
