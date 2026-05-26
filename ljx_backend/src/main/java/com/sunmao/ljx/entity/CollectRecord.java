package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 收藏记录实体类
 * 对应数据库表: collect_record
 * 作用：封装用户收藏文章记录相关的数据字段
 *
 * 收藏记录只关联文章，不关联帖子
 * 通过 user_id 和 article_id 的联合唯一索引防止重复收藏
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("collect_record") 指定对应的数据库表名为 collect_record
 */
@Data
@TableName("collect_record")
public class CollectRecord {

    /**
     * 收藏ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer collectId;

    /**
     * 收藏用户ID
     * 关联 user 表的 user_id
     * 表示这个收藏是由哪个用户操作的
     */
    private Integer userId;

    /**
     * 文章ID
     * 关联 article 表的 article_id
     * 表示收藏的是哪篇文章
     */
    private Integer articleId;

    /**
     * 收藏时间
     * 记录收藏操作的时间
     */
    private LocalDateTime createTime;
}
