package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论实体类
 * 对应数据库表: comment
 * 作用：封装评论相关的数据字段
 *
 * 评论支持多态关联，可以关联到文章或帖子
 * 通过 target_type 和 target_id 实现多态关联
 * 支持一级评论和二级回复（通过 parent_id 实现）
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("comment") 指定对应的数据库表名为 comment
 */
@Data
@TableName("comment")
public class Comment {

    /**
     * 评论ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论用户ID
     * 关联 user 表的 user_id
     * 表示这条评论是由哪个用户发布的
     */
    private Integer userId;

    /**
     * 评论目标类型
     * 1：文章
     * 2：帖子
     * 用于区分评论是关联到文章还是帖子
     */
    private Integer targetType;

    /**
     * 评论目标ID
     * 关联的目标对象ID
     * 当 target_type = 1 时，表示文章ID
     * 当 target_type = 2 时，表示帖子ID
     */
    private Integer targetId;

    /**
     * 父评论ID
     * 0：表示一级评论（直接评论文章或帖子）
     * 其他值：表示这是对某条评论的回复
     * 通过此字段实现评论的嵌套回复功能
     */
    private Integer parentId;

    /**
     * 评论内容
     * 评论的文字内容
     */
    private String content;

    /**
     * 点赞数
     * 记录评论被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 状态
     * 0：删除
     * 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     * 记录评论的发布时间
     */
    private LocalDateTime createTime;
}
