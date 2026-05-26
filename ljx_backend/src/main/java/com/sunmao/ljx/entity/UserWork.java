package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户作品实体类
 * 对应数据库表: user_work
 * 作用：封装用户发布的作品相关的数据字段
 *
 * 作品是用户在平台上展示的原创内容
 * 支持图片展示、点赞、评论等互动功能
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("user_work") 指定对应的数据库表名为 user_work
 */
@Data
@TableName("user_work")
public class UserWork {

    /**
     * 作品ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer workId;

    /**
     * 发布用户ID
     * 关联 user 表的 user_id
     * 表示这个作品是由哪个用户发布的
     */
    private Integer userId;

    /**
     * 作品标题
     * 作品的名称
     */
    private String title;

    /**
     * 作品描述
     * 作品的详细介绍
     */
    private String description;

    /**
     * 作品图片列表
     * 作品中包含的多张图片，使用 JSON 格式存储
     * 例如：["url1", "url2"]
     */
    private String images;

    /**
     * 点赞数
     * 记录作品被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论数
     * 记录作品被评论的总次数
     */
    private Integer commentCount;

    /**
     * 状态
     * 0：删除
     * 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     * 记录作品的发布时间
     */
    private LocalDateTime createTime;
}
