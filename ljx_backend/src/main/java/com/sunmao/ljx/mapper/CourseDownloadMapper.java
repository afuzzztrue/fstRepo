package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.CourseDownload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 课程下载记录数据访问层（Mapper）
 * 作用：定义对课程下载记录表（course_download）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<CourseDownload> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface CourseDownloadMapper extends BaseMapper<CourseDownload> {

    /**
     * 查询用户对指定课程的下载记录
     * 用于判断用户是否已下载该课程，防止重复记录下载次数
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM course_download：查询 course_download 表的所有字段
     * - WHERE user_id = #{userId}：筛选指定用户的下载记录
     * - AND course_id = #{courseId}：筛选指定课程的下载记录
     * - LIMIT 1：只返回一条记录（每个用户对同一课程只能下载一次）
     *
     * @param userId   用户ID
     * @param courseId 课程ID
     * @return 下载记录对象，如果不存在则返回 null
     */
    @Select("SELECT * FROM course_download WHERE user_id = #{userId} AND course_id = #{courseId} LIMIT 1")
    CourseDownload selectByUserAndCourse(@Param("userId") Integer userId, @Param("courseId") Integer courseId);
}
