package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 内容文章实体类
 * 对应数据库表: article
 * 功能说明: 存储首页内容卡片、热门内容、分类下的文章信息
 * 关联关系: 与Category（多对一）、User（多对一）关联，与LikeRecord、CollectRecord、Footprint存在被关联关系
 */
@Data
@TableName("article")
public class Article {

    /**
     * 文章ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    /**
     * 所属分类ID
     * 关联category表，标识文章所属的分类
     */
    private Integer categoryId;

    /**
     * 发布者用户ID
     * 关联user表，标识文章的发布者
     */
    private Integer userId;

    /**
     * 文章标题
     * 文章的展示标题，如"太和殿：榫卯之巅"
     */
    private String title;

    /**
     * 文章摘要
     * 文章的简短描述，用于列表页展示
     */
    private String summary;

    /**
     * 文章内容（富文本/HTML）
     * 文章的完整正文内容
     */
    private String content;

    /**
     * 封面图片URL
     * 文章在列表中展示的封面图
     */
    private String coverImage;

    /**
     * 文章图片列表（JSON格式）
     * 存储文章内多张图片的URL数组
     */
    private String images;

    /**
     * 标签，逗号分隔
     * 如：宫殿建筑,榫卯，用于内容检索和推荐
     */
    private String tags;

    /**
     * 地理位置
     * 如：北京·故宫，标识文章内容的地理信息
     */
    private String location;

    /**
     * 浏览次数
     * 统计文章被查看的总次数
     */
    private Integer viewCount;

    /**
     * 点赞次数
     * 统计文章被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论次数
     * 统计文章被评论的总次数
     */
    private Integer commentCount;

    /**
     * 收藏次数
     * 统计文章被收藏的总次数
     */
    private Integer collectCount;

    /**
     * 是否热门：0否 1是
     * 控制文章是否在首页热门区域展示
     */
    private Integer isHot;

    /**
     * 是否轮播：0否 1是
     * 控制文章是否作为轮播内容展示
     */
    private Integer isBanner;

    /**
     * 排序号
     * 控制文章在列表中的展示顺序
     */
    private Integer sortOrder;

    /**
     * 状态：0草稿 1已发布 2下架
     * 控制文章的发布状态
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
