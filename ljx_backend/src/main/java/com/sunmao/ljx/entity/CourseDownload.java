package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程下载记录实体类
 * 对应数据库表: course_download
 * 功能说明: 记录用户下载课程的行为，用于"已下载的课程"功能
 * 关联关系: 与User（多对一）、Course（多对一）关联
 */
@Data
@TableName("course_download")
public class CourseDownload {

    /**
     * 下载ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer downloadId;

    /**
     * 用户ID
     * 关联user表，标识下载课程的用户
     */
    private Integer userId;

    /**
     * 课程ID
     * 关联course表，标识被下载的课程
     */
    private Integer courseId;

    /**
     * 下载时间
     * 记录用户下载课程的时间
     */
    private LocalDateTime createTime;
}
