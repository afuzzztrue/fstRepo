package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论实体类
 * 对应数据库表: comment
 * 功能说明: 记录用户对文章或帖子的评论内容，支持一级评论和回复（二级评论）
 * 关联关系: 与User（多对一）关联，通过target_type和target_id多态关联Article或Post，parentId实现自关联回复
 */
@Data
@TableName("comment")
public class Comment {

    /**
     * 评论ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论用户ID
     * 关联user表，标识发表评论的用户
     */
    private Integer userId;

    /**
     * 评论目标类型：1文章 2帖子
     * 多态关联标识，区分评论的对象类型
     * 1表示评论的是article表中的文章
     * 2表示评论的是post表中的帖子
     */
    private Integer targetType;

    /**
     * 评论目标ID
     * 对应被评论文章或帖子的ID
     */
    private Integer targetId;

    /**
     * 父评论ID，0为一级评论
     * 实现评论的层级回复功能：
     * - 值为0时表示一级评论（直接评论文章/帖子）
     * - 值为其他评论ID时表示回复该评论
     */
    private Integer parentId;

    /**
     * 评论内容
     * 用户输入的评论文字
     */
    private String content;

    /**
     * 点赞数
     * 统计评论被点赞的次数
     */
    private Integer likeCount;

    /**
     * 状态：0删除 1正常
     * 控制评论是否可见
     */
    private Integer status;

    /**
     * 创建时间
     * 记录评论的发表时间
     */
    private LocalDateTime createTime;
}
