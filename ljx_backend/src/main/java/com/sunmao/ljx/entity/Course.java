package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 教程课程实体类
 * 对应数据库表: course
 * 功能说明: 存储技艺教程模块的课程信息，支持视频课程管理和难度分级
 * 关联关系: 与Category（多对一）、User（多对一，讲师）关联，与CourseDownload存在被关联关系
 */
@Data
@TableName("course")
public class Course {

    /**
     * 课程ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer courseId;

    /**
     * 所属分类ID
     * 关联category表，标识课程所属的分类
     */
    private Integer categoryId;

    /**
     * 讲师用户ID
     * 关联user表，标识课程的发布讲师
     */
    private Integer userId;

    /**
     * 课程标题
     * 课程的展示名称，如"入门教程"、"大师讲堂"
     */
    private String title;

    /**
     * 课程描述
     * 课程的详细介绍说明
     */
    private String description;

    /**
     * 封面图片
     * 课程在列表中展示的封面图
     */
    private String coverImage;

    /**
     * 视频URL
     * 课程视频资源的网络地址
     */
    private String videoUrl;

    /**
     * 课程时长（分钟）
     * 记录视频课程的总时长
     */
    private Integer duration;

    /**
     * 难度：1入门 2进阶 3高级 4大师
     * 标识课程的学习难度等级，对应前端分类页的入门、进阶、高级、大师
     */
    private Integer difficulty;

    /**
     * 观看次数
     * 统计课程被观看的总次数
     */
    private Integer viewCount;

    /**
     * 点赞数
     * 统计课程被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 下载次数
     * 统计课程被下载的总次数
     */
    private Integer downloadCount;

    /**
     * 排序号
     * 控制课程在列表中的展示顺序
     */
    private Integer sortOrder;

    /**
     * 状态：0下架 1上架
     * 控制课程是否在前端展示
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
