package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户作品实体类
 * 对应数据库表: user_work
 * 功能说明: 存储用户发布的作品展示信息，用于"我的作品"功能
 * 关联关系: 与User（多对一）关联
 */
@Data
@TableName("user_work")
public class UserWork {

    /**
     * 作品ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer workId;

    /**
     * 用户ID
     * 关联user表，标识作品的发布者
     */
    private Integer userId;

    /**
     * 作品标题
     * 用户作品的名称
     */
    private String title;

    /**
     * 作品描述
     * 用户对作品的详细说明
     */
    private String description;

    /**
     * 作品图片（JSON格式）
     * 存储作品的多张展示图片URL数组
     */
    private String images;

    /**
     * 点赞数
     * 统计作品被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论数
     * 统计作品被评论的总次数
     */
    private Integer commentCount;

    /**
     * 状态：0删除 1正常
     * 控制作品是否可见
     */
    private Integer status;

    /**
     * 创建时间
     * 记录作品的发布时间
     */
    private LocalDateTime createTime;
}
