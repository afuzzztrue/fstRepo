package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 浏览足迹实体类
 * 对应数据库表: footprint
 * 功能说明: 记录用户浏览文章的历史，用于"我的足迹"功能
 * 关联关系: 与User（多对一）、Article（多对一）关联
 */
@Data
@TableName("footprint")
public class Footprint {

    /**
     * 足迹ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer footprintId;

    /**
     * 用户ID
     * 关联user表，标识浏览文章的用户
     */
    private Integer userId;

    /**
     * 文章ID
     * 关联article表，标识被浏览的文章
     */
    private Integer articleId;

    /**
     * 浏览时间
     * 记录用户浏览文章的时间点
     */
    private LocalDateTime createTime;
}
