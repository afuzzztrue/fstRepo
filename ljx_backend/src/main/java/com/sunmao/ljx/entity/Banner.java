package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 轮播图实体类
 * 对应数据库表: banner
 * 作用：封装首页轮播图相关的数据字段
 *
 * 轮播图用于首页顶部展示，可以配置跳转链接
 * 支持跳转到文章详情页或外部链接
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("banner") 指定对应的数据库表名为 banner
 */
@Data
@TableName("banner")
public class Banner {

    /**
     * 轮播图ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer bannerId;

    /**
     * 轮播图标题
     * 轮播图上显示的标题文字
     */
    private String title;

    /**
     * 图片URL
     * 轮播图展示的图片地址
     */
    private String imageUrl;

    /**
     * 跳转链接
     * 点击轮播图后跳转的URL地址
     */
    private String linkUrl;

    /**
     * 跳转类型
     * 1：跳转到文章详情页
     * 2：跳转到外部链接
     */
    private Integer linkType;

    /**
     * 关联ID
     * 当 link_type = 1 时，表示关联的文章ID
     * 当 link_type = 2 时，此字段可为空
     */
    private Integer linkId;

    /**
     * 排序号
     * 用于控制轮播图的显示顺序
     * 数字越小，排序越靠前
     */
    private Integer sortOrder;

    /**
     * 状态
     * 0：禁用
     * 1：启用
     */
    private Integer status;

    /**
     * 创建时间
     * 记录轮播图的创建时间
     */
    private LocalDateTime createTime;
}
