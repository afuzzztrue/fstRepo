package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.CollectRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 收藏记录数据访问层（Mapper）
 * 作用：定义对收藏记录表（collect_record）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<CollectRecord> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface CollectRecordMapper extends BaseMapper<CollectRecord> {

    /**
     * 查询用户对指定文章的收藏记录
     * 用于判断用户是否已收藏，实现收藏/取消收藏的切换功能
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM collect_record：查询 collect_record 表的所有字段
     * - WHERE user_id = #{userId}：筛选指定用户的收藏记录
     * - AND article_id = #{articleId}：筛选指定文章的收藏记录
     * - LIMIT 1：只返回一条记录（每个用户对同一文章只能收藏一次）
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return 收藏记录对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM collect_record WHERE user_id = #{userId} AND article_id = #{articleId} LIMIT 1")
    CollectRecord selectByUserAndArticle(@Param("userId") Integer userId, @Param("articleId") Integer articleId);
}
