package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 动态帖子实体类
 * 对应数据库表: post
 * 作用：封装动态广场帖子相关的数据字段
 *
 * 帖子是用户在动态广场发布的内容，类似于朋友圈或微博
 * 支持文字、图片等内容形式
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("post") 指定对应的数据库表名为 post
 */
@Data
@TableName("post")
public class Post {

    /**
     * 帖子ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 发布用户ID
     * 关联 user 表的 user_id
     * 表示这条帖子是由哪个用户发布的
     */
    private Integer userId;

    /**
     * 帖子内容
     * 帖子的文字内容
     */
    private String content;

    /**
     * 图片列表
     * 帖子中包含的多张图片，使用 JSON 格式存储
     * 例如：["url1", "url2"]
     */
    private String images;

    /**
     * 发布位置
     * 用户发布帖子时的地理位置信息
     */
    private String location;

    /**
     * 点赞数
     * 记录帖子被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论数
     * 记录帖子被评论的总次数
     */
    private Integer commentCount;

    /**
     * 分享数
     * 记录帖子被分享的总次数
     */
    private Integer shareCount;

    /**
     * 帖子类型
     * 0：普通帖子
     * 1：热门帖子
     */
    private Integer postType;

    /**
     * 状态
     * 0：删除
     * 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     * 记录帖子的发布时间
     */
    private LocalDateTime createTime;
}
