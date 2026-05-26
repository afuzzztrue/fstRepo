package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.LikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 点赞记录数据访问层（Mapper）
 * 作用：定义对点赞记录表（like_record）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<LikeRecord> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface LikeRecordMapper extends BaseMapper<LikeRecord> {

    /**
     * 查询用户对指定目标的点赞记录
     * 用于判断用户是否已点赞，实现点赞/取消点赞的切换功能
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM like_record：查询 like_record 表的所有字段
     * - WHERE user_id = #{userId}：筛选指定用户的点赞记录
     * - AND target_type = #{targetType}：筛选指定目标类型（1：文章、2：帖子）
     * - AND target_id = #{targetId}：筛选指定目标ID
     * - LIMIT 1：只返回一条记录（每个用户对同一目标只能点赞一次）
     *
     * @param userId     用户ID
     * @param targetType 目标类型（1：文章、2：帖子）
     * @param targetId   目标ID
     * @return 点赞记录对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM like_record WHERE user_id = #{userId} AND target_type = #{targetType} AND target_id = #{targetId} LIMIT 1")
    LikeRecord selectByUserAndTarget(@Param("userId") Integer userId,
                                      @Param("targetType") Integer targetType,
                                      @Param("targetId") Integer targetId);
}
