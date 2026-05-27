package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 动态帖子实体类
 * 对应数据库表: post
 * 功能说明: 存储动态广场的用户发布内容，支持图文发布和社交互动
 * 关联关系: 与User（多对一）关联，与LikeRecord、Comment存在被关联关系
 */
@Data
@TableName("post")
public class Post {

    /**
     * 帖子ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 发布用户ID
     * 关联user表，标识帖子的发布者
     */
    private Integer userId;

    /**
     * 帖子内容
     * 用户发布的文字内容，如动态描述、心得分享等
     */
    private String content;

    /**
     * 图片列表（JSON格式）
     * 帖子中包含的多张图片URL数组
     */
    private String images;

    /**
     * 发布位置
     * 用户发布帖子时的地理位置信息
     */
    private String location;

    /**
     * 点赞数
     * 统计帖子被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论数
     * 统计帖子被评论的总次数
     */
    private Integer commentCount;

    /**
     * 分享数
     * 统计帖子被分享的总次数
     */
    private Integer shareCount;

    /**
     * 帖子类型：0普通 1热门
     * 控制帖子是否在热门区域展示
     */
    private Integer postType;

    /**
     * 状态：0删除 1正常
     * 控制帖子是否可见
     */
    private Integer status;

    /**
     * 创建时间
     * 记录帖子的发布时间
     */
    private LocalDateTime createTime;
}
