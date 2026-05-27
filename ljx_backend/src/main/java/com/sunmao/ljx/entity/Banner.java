package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 轮播图实体类
 * 对应数据库表: banner
 * 功能说明: 管理首页轮播图内容，支持跳转到文章或外部链接
 * 关联关系: 通过link_type和link_id关联到Article或其他资源
 */
@Data
@TableName("banner")
public class Banner {

    /**
     * 轮播图ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer bannerId;

    /**
     * 轮播图标题
     * 轮播图的展示标题
     */
    private String title;

    /**
     * 图片URL
     * 轮播图展示的图片地址
     */
    private String imageUrl;

    /**
     * 跳转链接
     * 点击轮播图后的跳转目标地址
     */
    private String linkUrl;

    /**
     * 跳转类型：1文章 2外部链接
     * 标识点击轮播图后的跳转方式
     * 1表示跳转到平台内的文章详情页
     * 2表示跳转到外部网页
     */
    private Integer linkType;

    /**
     * 关联ID
     * 当link_type为1时，对应article表的article_id
     */
    private Integer linkId;

    /**
     * 排序号
     * 控制轮播图的展示顺序，数值越小越靠前
     */
    private Integer sortOrder;

    /**
     * 状态：0禁用 1启用
     * 控制轮播图是否在前端展示
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
