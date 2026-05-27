package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 内容分类实体类
 * 对应数据库表: category
 * 功能说明: 采用自关联树形结构存储内容分类，如结构、家具、木料、历史、教程
 * 关联关系: 与Article、Course存在一对多关系，parentId指向父分类实现树形结构
 */
@Data
@TableName("category")
public class Category {

    /**
     * 分类ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称
     * 如：结构、家具、木料、历史、教程
     */
    private String name;

    /**
     * 父分类ID，0表示根分类
     * 通过自关联实现树形分类结构，支持多级分类扩展
     */
    private Integer parentId;

    /**
     * 排序号
     * 控制分类在前端展示的顺序，数值越小越靠前
     */
    private Integer sortOrder;

    /**
     * 分类图标URL
     * 分类页面展示的分类图标
     */
    private String icon;

    /**
     * 分类描述
     * 对分类内容的简要说明
     */
    private String description;

    /**
     * 状态：0禁用 1启用
     * 控制分类是否在前端展示
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
