package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.CourseDownload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 课程下载记录数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供CourseDownload实体的基础CRUD操作
 * 扩展方法: 提供根据用户ID和课程ID查询下载记录的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface CourseDownloadMapper extends BaseMapper<CourseDownload> {

    /**
     * 根据用户ID和课程ID查询下载记录
     * 使用场景: 判断用户是否已下载某门课程，避免重复记录下载次数
     * SQL说明: 查询course_download表中user_id和course_id同时匹配的记录，LIMIT 1确保只返回一条
     *
     * @param userId 用户ID，标识下载课程的用户
     * @param courseId 课程ID，标识被下载的课程
     * @return 匹配的下载记录对象，不存在则返回null（表示用户未下载过）
     */
    @Select("SELECT * FROM course_download WHERE user_id = #{userId} AND course_id = #{courseId} LIMIT 1")
    CourseDownload selectByUserAndCourse(@Param("userId") Integer userId, @Param("courseId") Integer courseId);
}
