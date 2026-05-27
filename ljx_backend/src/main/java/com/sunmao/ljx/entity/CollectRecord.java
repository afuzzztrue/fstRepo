package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 收藏记录实体类
 * 对应数据库表: collect_record
 * 功能说明: 记录用户对文章的收藏行为
 * 关联关系: 与User（多对一）、Article（多对一）关联
 */
@Data
@TableName("collect_record")
public class CollectRecord {

    /**
     * 收藏ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户ID
     * 关联user表，标识执行收藏操作的用户
     */
    private Integer userId;

    /**
     * 文章ID
     * 关联article表，标识被收藏的文章
     */
    private Integer articleId;

    /**
     * 收藏时间
     * 记录用户执行收藏操作的时间
     */
    private LocalDateTime createTime;
}
