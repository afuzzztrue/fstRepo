package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程下载记录实体类
 * 对应数据库表: course_download
 * 作用：封装用户下载课程记录相关的数据字段
 *
 * 下载记录用于统计课程的下载次数
 * 通过 user_id 和 course_id 的联合唯一索引防止重复记录
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("course_download") 指定对应的数据库表名为 course_download
 */
@Data
@TableName("course_download")
public class CourseDownload {

    /**
     * 下载ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer downloadId;

    /**
     * 下载用户ID
     * 关联 user 表的 user_id
     * 表示这个下载记录属于哪个用户
     */
    private Integer userId;

    /**
     * 课程ID
     * 关联 course 表的 course_id
     * 表示下载的是哪门课程
     */
    private Integer courseId;

    /**
     * 下载时间
     * 记录下载操作的时间
     */
    private LocalDateTime createTime;
}
