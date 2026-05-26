package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 浏览足迹实体类
 * 对应数据库表: footprint
 * 作用：封装用户浏览文章足迹相关的数据字段
 *
 * 足迹记录用户浏览过的文章，用于"我的足迹"功能
 * 只记录文章浏览，不记录帖子浏览
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("footprint") 指定对应的数据库表名为 footprint
 */
@Data
@TableName("footprint")
public class Footprint {

    /**
     * 足迹ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer footprintId;

    /**
     * 浏览用户ID
     * 关联 user 表的 user_id
     * 表示这个浏览记录属于哪个用户
     */
    private Integer userId;

    /**
     * 文章ID
     * 关联 article 表的 article_id
     * 表示浏览的是哪篇文章
     */
    private Integer articleId;

    /**
     * 浏览时间
     * 记录浏览操作的时间
     */
    private LocalDateTime createTime;
}
