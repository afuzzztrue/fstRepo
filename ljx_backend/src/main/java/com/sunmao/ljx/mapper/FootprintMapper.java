package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Footprint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 浏览足迹数据访问层（Mapper）
 * 作用：定义对足迹表（footprint）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Footprint> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface FootprintMapper extends BaseMapper<Footprint> {

    /**
     * 查询用户的最近浏览足迹
     * 用于"我的足迹"功能展示
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM footprint：查询 footprint 表的所有字段
     * - WHERE user_id = #{userId}：筛选指定用户的足迹
     * - ORDER BY create_time DESC：按浏览时间降序排列（最近浏览的在前面）
     * - LIMIT #{limit}：限制返回的记录数
     *
     * @param userId 用户ID
     * @param limit  返回的记录数限制
     * @return 用户的浏览足迹列表
     */
    @Select("SELECT * FROM footprint WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT #{limit}")
    List<Footprint> selectRecentByUserId(@Param("userId") Integer userId, @Param("limit") Integer limit);
}
