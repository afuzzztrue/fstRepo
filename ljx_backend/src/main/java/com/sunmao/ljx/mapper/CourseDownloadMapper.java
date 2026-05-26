package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.CourseDownload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 课程下载记录数据访问层
 */
@Mapper
public interface CourseDownloadMapper extends BaseMapper<CourseDownload> {

    @Select("SELECT * FROM course_download WHERE user_id = #{userId} AND course_id = #{courseId} LIMIT 1")
    CourseDownload selectByUserAndCourse(@Param("userId") Integer userId, @Param("courseId") Integer courseId);
}
