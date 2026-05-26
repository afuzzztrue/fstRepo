package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库表: user
 * 作用：封装用户相关的所有数据字段，作为数据传输和持久化的载体
 *
 * 实体类的作用：
 * 1. 与数据库表结构一一对应，每个字段对应表中的一列
 * 2. 作为 MyBatis Plus 进行 CRUD 操作的数据载体
 * 3. 在 Controller、Service、Mapper 各层之间传递数据
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * 这样就不需要在代码中手动编写这些方法，减少样板代码
 *
 * @TableName("user") 指定该实体类对应的数据库表名为 user
 * 如果类名和表名一致（忽略大小写），可以省略此注解
 */
@Data
@TableName("user")
public class User {

    /**
     * 用户ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库的自增策略生成主键
     * 插入数据时不需要手动设置 userId，数据库会自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 微信openid
     * 微信用户的唯一标识，用于微信登录
     * 每个微信用户在一个小程序中的 openid 是唯一的
     */
    private String openid;

    /**
     * 微信unionid
     * 微信开放平台下的统一标识
     * 同一个微信用户在同一开放平台下的不同应用中，unionid 是相同的
     * 可用于打通多个应用的账号体系
     */
    private String unionid;

    /**
     * 用户昵称
     * 用户在平台上显示的姓名
     */
    private String nickname;

    /**
     * 头像URL
     * 用户头像图片的网络地址
     */
    private String avatar;

    /**
     * 手机号
     * 用户绑定的手机号码，可用于账号登录
     */
    private String phone;

    /**
     * 邮箱
     * 用户绑定的电子邮箱，可用于账号登录
     */
    private String email;

    /**
     * 登录密码
     * 存储的是加密后的密码，不能存储明文密码
     * 本项目使用 MD5 加密
     */
    private String password;

    /**
     * 性别
     * 0：未知
     * 1：男
     * 2：女
     */
    private Integer gender;

    /**
     * 省份
     * 用户所在省份
     */
    private String province;

    /**
     * 城市
     * 用户所在城市
     */
    private String city;

    /**
     * 学习时长（小时）
     * 记录用户在平台上的累计学习时长
     */
    private Integer studyHours;

    /**
     * 用户类型
     * 0：普通用户
     * 1：传承人（非遗传承人身份）
     * 2：管理员
     */
    private Integer userType;

    /**
     * 状态
     * 0：禁用（账号被冻结）
     * 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     * 记录账号的注册时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     * 记录账号信息的最后修改时间
     */
    private LocalDateTime updateTime;
}
