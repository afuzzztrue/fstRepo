package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 教程课程实体类
 * 对应数据库表: course
 * 作用：封装技艺教程课程相关的数据字段
 *
 * 课程是技艺教程模块的核心内容，包含视频课程信息
 * 支持按分类和难度进行筛选
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("course") 指定对应的数据库表名为 course
 */
@Data
@TableName("course")
public class Course {

    /**
     * 课程ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer courseId;

    /**
     * 所属分类ID
     * 关联 category 表的 category_id
     * 表示这门课程属于哪个分类
     */
    private Integer categoryId;

    /**
     * 讲师用户ID
     * 关联 user 表的 user_id
     * 表示这门课程是由哪个用户（讲师）发布的
     */
    private Integer userId;

    /**
     * 课程标题
     * 课程的名称
     */
    private String title;

    /**
     * 课程描述
     * 课程的详细介绍
     */
    private String description;

    /**
     * 封面图片
     * 课程在列表中显示的封面图片地址
     */
    private String coverImage;

    /**
     * 视频URL
     * 课程视频的网络地址
     */
    private String videoUrl;

    /**
     * 课程时长（分钟）
     * 课程视频的总时长
     */
    private Integer duration;

    /**
     * 难度等级
     * 1：入门
     * 2：进阶
     * 3：高级
     * 4：大师
     */
    private Integer difficulty;

    /**
     * 观看次数
     * 记录课程被观看的总次数
     */
    private Integer viewCount;

    /**
     * 点赞数
     * 记录课程被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 下载次数
     * 记录课程被下载的总次数
     */
    private Integer downloadCount;

    /**
     * 排序号
     * 用于控制课程在列表中的显示顺序
     * 数字越小，排序越靠前
     */
    private Integer sortOrder;

    /**
     * 状态
     * 0：下架
     * 1：上架
     */
    private Integer status;

    /**
     * 创建时间
     * 记录课程的创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     * 记录课程信息的最后修改时间
     */
    private LocalDateTime updateTime;
}
