package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 内容文章实体类
 * 对应数据库表: article
 * 作用：封装文章内容相关的所有数据字段
 *
 * 文章是本项目的核心内容之一，包括：
 * - 首页展示的热门文章
 * - 各分类下的内容文章
 * - 轮播图关联的文章
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("article") 指定对应的数据库表名为 article
 */
@Data
@TableName("article")
public class Article {

    /**
     * 文章ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    /**
     * 所属分类ID
     * 关联 category 表的 category_id
     * 表示这篇文章属于哪个分类（结构、家具、木料、历史、教程）
     */
    private Integer categoryId;

    /**
     * 发布者用户ID
     * 关联 user 表的 user_id
     * 表示这篇文章是由哪个用户发布的
     */
    private Integer userId;

    /**
     * 文章标题
     * 文章的标题，用于列表展示和搜索
     */
    private String title;

    /**
     * 文章摘要
     * 文章的简短描述，用于列表页展示
     */
    private String summary;

    /**
     * 文章内容
     * 文章的完整内容，使用富文本或 HTML 格式存储
     */
    private String content;

    /**
     * 封面图片URL
     * 文章在列表中显示的封面图片地址
     */
    private String coverImage;

    /**
     * 文章图片列表
     * 存储文章中的多张图片，使用 JSON 格式存储
     * 例如：["url1", "url2", "url3"]
     */
    private String images;

    /**
     * 标签
     * 文章的标签，使用逗号分隔
     * 例如："宫殿建筑,榫卯"
     */
    private String tags;

    /**
     * 地理位置
     * 文章关联的地理位置信息
     * 例如："北京·故宫"
     */
    private String location;

    /**
     * 浏览次数
     * 记录文章被浏览的总次数
     */
    private Integer viewCount;

    /**
     * 点赞次数
     * 记录文章被点赞的总次数
     */
    private Integer likeCount;

    /**
     * 评论次数
     * 记录文章被评论的总次数
     */
    private Integer commentCount;

    /**
     * 收藏次数
     * 记录文章被收藏的总次数
     */
    private Integer collectCount;

    /**
     * 是否热门
     * 0：否
     * 1：是
     * 热门文章会在首页特别展示
     */
    private Integer isHot;

    /**
     * 是否轮播
     * 0：否
     * 1：是
     * 轮播文章会在首页轮播图区域展示
     */
    private Integer isBanner;

    /**
     * 排序号
     * 用于控制文章在列表中的显示顺序
     * 数字越小，排序越靠前
     */
    private Integer sortOrder;

    /**
     * 状态
     * 0：草稿（未发布）
     * 1：已发布
     * 2：下架
     */
    private Integer status;

    /**
     * 创建时间
     * 记录文章的发布时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     * 记录文章信息的最后修改时间
     */
    private LocalDateTime updateTime;
}
