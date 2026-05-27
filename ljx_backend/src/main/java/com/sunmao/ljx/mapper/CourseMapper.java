package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 教程课程数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Course实体的基础CRUD操作
 * 扩展方法: 提供按分类查询课程、按难度查询课程、更新浏览量/下载量等方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Update - 使用注解方式编写SQL更新语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据分类ID查询课程列表
     * 使用场景: 教程页面按分类筛选课程
     * SQL说明: 查询category_id匹配且status=1（上架状态）的课程，按sort_order和create_time降序排列
     *
     * @param categoryId 分类ID，对应category表的category_id
     * @return 该分类下的课程列表
     */
    @Select("SELECT * FROM course WHERE category_id = #{categoryId} AND status = 1 ORDER BY sort_order, create_time DESC")
    List<Course> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 根据难度等级查询课程列表
     * 使用场景: 教程页面按难度筛选课程（入门、进阶、高级、大师）
     * SQL说明: 查询difficulty匹配且status=1（上架状态）的课程，按sort_order排序
     * 难度说明: 1=入门, 2=进阶, 3=高级, 4=大师
     *
     * @param difficulty 难度等级，取值范围1-4
     * @return 该难度等级的课程列表
     */
    @Select("SELECT * FROM course WHERE difficulty = #{difficulty} AND status = 1 ORDER BY sort_order")
    List<Course> selectByDifficulty(@Param("difficulty") Integer difficulty);

    /**
     * 增加课程浏览次数
     * 使用场景: 用户查看课程详情时，浏览量+1
     * SQL说明: 使用UPDATE语句将view_count字段值加1
     *
     * @param courseId 课程ID
     * @return 更新的行数
     */
    @Update("UPDATE course SET view_count = view_count + 1 WHERE course_id = #{courseId}")
    int incrementViewCount(@Param("courseId") Integer courseId);

    /**
     * 增加课程下载次数
     * 使用场景: 用户下载课程时，下载量+1
     * SQL说明: 使用UPDATE语句将download_count字段值加1
     *
     * @param courseId 课程ID
     * @return 更新的行数
     */
    @Update("UPDATE course SET download_count = download_count + 1 WHERE course_id = #{courseId}")
    int incrementDownloadCount(@Param("courseId") Integer courseId);
}
