package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 关注关系实体类
 * 对应数据库表: follow
 * 作用：封装用户关注关系相关的数据字段
 *
 * 关注关系是单向的，A 关注 B 不代表 B 关注 A
 * 通过 user_id 和 follow_user_id 的联合唯一索引防止重复关注
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 * @TableName("follow") 指定对应的数据库表名为 follow
 */
@Data
@TableName("follow")
public class Follow {

    /**
     * 关注ID，主键
     * @TableId 标识这是主键字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(type = IdType.AUTO)
    private Integer followId;

    /**
     * 关注者用户ID
     * 关联 user 表的 user_id
     * 表示发起关注的用户
     */
    private Integer userId;

    /**
     * 被关注用户ID
     * 关联 user 表的 user_id
     * 表示被关注的用户
     */
    private Integer followUserId;

    /**
     * 关注时间
     * 记录关注操作的时间
     */
    private LocalDateTime createTime;
}
