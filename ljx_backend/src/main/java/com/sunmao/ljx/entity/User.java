package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库表: user
 * 功能说明: 存储平台注册用户和微信用户信息，是系统的核心实体之一
 * 关联关系: 与Article、Post、Course、Comment、UserWork等多张表存在一对多关系
 */
@Data
@TableName("user")
public class User {

    /**
     * 用户ID，主键，自增
     * 使用MyBatis-Plus的AUTO策略，由数据库自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 微信openid，唯一标识
     * 用于微信小程序用户的唯一识别，与微信账号绑定
     */
    private String openid;

    /**
     * 微信unionid
     * 用于同一主体下多个微信应用之间的用户统一识别
     */
    private String unionid;

    /**
     * 用户昵称
     * 展示在小程序界面上的用户名称
     */
    private String nickname;

    /**
     * 头像URL
     * 用户头像图片的网络地址
     */
    private String avatar;

    /**
     * 手机号
     * 用户注册时填写的手机号码，用于账号登录和身份验证
     */
    private String phone;

    /**
     * 邮箱
     * 用户注册时填写的电子邮箱，可用于账号登录
     */
    private String email;

    /**
     * 登录密码（加密存储）
     * 使用MD5加密存储，不保存明文密码
     */
    private String password;

    /**
     * 性别：0未知 1男 2女
     * 用户性别标识，默认值为0（未知）
     */
    private Integer gender;

    /**
     * 省份
     * 用户所在省份信息
     */
    private String province;

    /**
     * 城市
     * 用户所在城市信息
     */
    private String city;

    /**
     * 学习时长（小时）
     * 记录用户在平台上的累计学习时长，用于个人中心展示
     */
    private Integer studyHours;

    /**
     * 用户类型：0普通用户 1传承人 2管理员
     * 区分不同角色的用户权限和功能
     */
    private Integer userType;

    /**
     * 状态：0禁用 1正常
     * 控制用户账号是否可用，默认1（正常）
     */
    private Integer status;

    /**
     * 创建时间
     * 记录用户账号的注册时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     * 记录用户信息的最后修改时间
     */
    private LocalDateTime updateTime;
}
