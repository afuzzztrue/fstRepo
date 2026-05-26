package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 内容分类实体类
 * 对应数据库表: category
 * 作用：封装内容分类相关的数据字段
 *
 * 分类是树形结构，支持多级分类：
 * - 一级分类：结构、家具、木料、历史、教程
 * - 可以通过 parent_id 实现二级、三级分类
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("category") 指定对应的数据库表名为 category
 */
@Data
@TableName("category")
public class Category {

    /**
     * 分类ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称
     * 例如：结构、家具、木料、历史、教程
     */
    private String name;

    /**
     * 父分类ID
     * 0 表示根分类（一级分类）
     * 其他值表示该分类的父分类ID
     * 通过此字段实现分类的树形结构
     */
    private Integer parentId;

    /**
     * 排序号
     * 用于控制分类的显示顺序
     * 数字越小，排序越靠前
     */
    private Integer sortOrder;

    /**
     * 分类图标URL
     * 分类在页面上显示的图标图片地址
     */
    private String icon;

    /**
     * 分类描述
     * 分类的简要说明
     */
    private String description;

    /**
     * 状态
     * 0：禁用
     * 1：启用
     */
    private Integer status;

    /**
     * 创建时间
     * 记录分类的创建时间
     */
    private LocalDateTime createTime;
}
