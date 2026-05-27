package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 点赞记录实体类
 * 对应数据库表: like_record
 * 功能说明: 记录用户对文章或帖子的点赞行为，支持多态关联（target_type + target_id）
 * 关联关系: 与User（多对一）关联，通过target_type和target_id多态关联Article或Post
 */
@Data
@TableName("like_record")
public class LikeRecord {

    /**
     * 点赞ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer likeId;

    /**
     * 用户ID
     * 关联user表，标识执行点赞操作的用户
     */
    private Integer userId;

    /**
     * 点赞目标类型：1文章 2帖子
     * 多态关联标识，区分点赞的对象类型
     * 1表示点赞的是article表中的文章
     * 2表示点赞的是post表中的帖子
     */
    private Integer targetType;

    /**
     * 点赞目标ID
     * 对应被点赞文章或帖子的ID
     */
    private Integer targetId;

    /**
     * 点赞时间
     * 记录用户执行点赞操作的时间
     */
    private LocalDateTime createTime;
}
