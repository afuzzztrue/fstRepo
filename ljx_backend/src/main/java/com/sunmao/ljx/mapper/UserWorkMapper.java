package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.UserWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户作品数据访问层（Mapper）
 * 作用：定义对用户作品表（user_work）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<UserWork> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface UserWorkMapper extends BaseMapper<UserWork> {

    /**
     * 根据用户ID查询作品列表
     * 用于用户主页展示该用户发布的作品
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM user_work：查询 user_work 表的所有字段
     * - WHERE user_id = #{userId}：筛选指定用户的作品
     * - AND status = 1：筛选正常的作品
     * - ORDER BY create_time DESC：按创建时间降序排列（最新的在前面）
     *
     * @param userId 用户ID
     * @return 该用户的作品列表
     */
    @Select("SELECT * FROM user_work WHERE user_id = #{userId} AND status = 1 ORDER BY create_time DESC")
    List<UserWork> selectByUserId(@Param("userId") Integer userId);
}
