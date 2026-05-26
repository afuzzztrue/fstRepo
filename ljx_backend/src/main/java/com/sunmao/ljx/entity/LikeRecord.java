package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 点赞记录实体类
 * 对应数据库表: like_record
 * 作用：封装点赞记录相关的数据字段
 *
 * 点赞记录支持多态关联，可以关联到文章或帖子
 * 通过 target_type 和 target_id 实现多态关联
 * 这种设计避免了为每种内容类型单独创建点赞表
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("like_record") 指定对应的数据库表名为 like_record
 */
@Data
@TableName("like_record")
public class LikeRecord {

    /**
     * 点赞ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer likeId;

    /**
     * 点赞用户ID
     * 关联 user 表的 user_id
     * 表示这个点赞是由哪个用户操作的
     */
    private Integer userId;

    /**
     * 点赞目标类型
     * 1：文章
     * 2：帖子
     * 用于区分点赞是关联到文章还是帖子
     */
    private Integer targetType;

    /**
     * 点赞目标ID
     * 关联的目标对象ID
     * 当 target_type = 1 时，表示文章ID
     * 当 target_type = 2 时，表示帖子ID
     */
    private Integer targetId;

    /**
     * 点赞时间
     * 记录点赞操作的时间
     */
    private LocalDateTime createTime;
}
