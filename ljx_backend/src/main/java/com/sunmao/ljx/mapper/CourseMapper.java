package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 教程课程数据访问层（Mapper）
 * 作用：定义对课程表（course）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Course> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据分类ID查询课程列表
     * 用于分类页面展示该分类下的课程
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM course：查询 course 表的所有字段
     * - WHERE category_id = #{categoryId}：筛选指定分类的课程
     * - AND status = 1：筛选上架的课程
     * - ORDER BY sort_order, create_time DESC：先按排序号升序，再按创建时间降序
     *
     * @param categoryId 分类ID
     * @return 该分类下的课程列表
     */
    @Select("SELECT * FROM course WHERE category_id = #{categoryId} AND status = 1 ORDER BY sort_order, create_time DESC")
    List<Course> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 根据难度等级查询课程列表
     * 用于按难度筛选课程
     *
     * SQL 说明：
     * - WHERE difficulty = #{difficulty}：筛选指定难度的课程
     * - AND status = 1：筛选上架的课程
     * - ORDER BY sort_order：按排序号升序排列
     *
     * @param difficulty 难度等级（1：入门、2：进阶、3：高级、4：大师）
     * @return 该难度下的课程列表
     */
    @Select("SELECT * FROM course WHERE difficulty = #{difficulty} AND status = 1 ORDER BY sort_order")
    List<Course> selectByDifficulty(@Param("difficulty") Integer difficulty);

    /**
     * 增加课程观看次数
     * 用于记录课程的观看量
     *
     * @Update 注解说明：
     * - 标识这是一个更新操作的 SQL
     * - 执行后会返回受影响的行数
     *
     * SQL 说明：
     * - UPDATE course：更新 course 表
     * - SET view_count = view_count + 1：将观看次数加 1
     * - WHERE course_id = #{courseId}：指定要更新的课程
     *
     * @param courseId 课程ID
     * @return 受影响的行数
     */
    @Update("UPDATE course SET view_count = view_count + 1 WHERE course_id = #{courseId}")
    int incrementViewCount(@Param("courseId") Integer courseId);

    /**
     * 增加课程下载次数
     * 用于记录课程的下载量
     *
     * SQL 说明：
     * - SET download_count = download_count + 1：将下载次数加 1
     * - WHERE course_id = #{courseId}：指定要更新的课程
     *
     * @param courseId 课程ID
     * @return 受影响的行数
     */
    @Update("UPDATE course SET download_count = download_count + 1 WHERE course_id = #{courseId}")
    int incrementDownloadCount(@Param("courseId") Integer courseId);
}
