package com.sunmao.ljx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 关注关系实体类
 * 对应数据库表: follow
 * 功能说明: 记录用户之间的关注关系，实现社交关注/粉丝功能
 * 关联关系: userId关联User（关注者），followUserId关联User（被关注者）
 */
@Data
@TableName("follow")
public class Follow {

    /**
     * 关注ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer followId;

    /**
     * 关注者用户ID
     * 关联user表，标识主动关注别人的用户
     */
    private Integer userId;

    /**
     * 被关注用户ID
     * 关联user表，标识被别人关注的用户
     */
    private Integer followUserId;

    /**
     * 关注时间
     * 记录关注关系建立的时间
     */
    private LocalDateTime createTime;
}
