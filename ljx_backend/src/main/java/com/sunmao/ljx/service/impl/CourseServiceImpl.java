package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Course;
import com.sunmao.ljx.entity.CourseDownload;
import com.sunmao.ljx.mapper.CourseDownloadMapper;
import com.sunmao.ljx.mapper.CourseMapper;
import com.sunmao.ljx.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 教程课程服务实现类
 * 作用：实现教程课程相关的业务逻辑
 *
 * Service 层是三层架构中的业务逻辑层，负责处理业务规则
 * 主要工作：
 * 1. 接收 Controller 传递的参数
 * 2. 调用 Mapper 层进行数据操作
 * 3. 处理业务逻辑（如校验、计算、转换等）
 * 4. 返回处理结果
 *
 * @Service 注解说明：
 * - 标识这是一个 Spring 的 Service 组件
 * - Spring 会自动扫描并创建该类的 Bean 实例
 * - 可以被 @Autowired 注入到 Controller 中
 *
 * 继承 ServiceImpl<CourseMapper, Course> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 CourseService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    /**
     * 课程下载记录 Mapper
     * 用于操作课程下载记录表
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Mapper 接口
     */
    @Resource
    private CourseDownloadMapper courseDownloadMapper;

    /**
     * 根据分类ID查询课程列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param categoryId 分类ID
     * @return 该分类下的课程列表
     */
    @Override
    public List<Course> getListByCategoryId(Integer categoryId) {
        // 调用 CourseMapper 的自定义方法查询分类课程
        return baseMapper.selectByCategoryId(categoryId);
    }

    /**
     * 根据难度等级查询课程列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param difficulty 难度等级（1：入门、2：进阶、3：高级、4：大师）
     * @return 该难度下的课程列表
     */
    @Override
    public List<Course> getListByDifficulty(Integer difficulty) {
        // 调用 CourseMapper 的自定义方法查询难度课程
        return baseMapper.selectByDifficulty(difficulty);
    }

    /**
     * 下载课程
     * 记录用户的课程下载行为
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     * - 保证下载记录和课程下载数的一致性
     *
     * 业务逻辑：
     * 1. 查询用户是否已下载该课程
     * 2. 如果未下载，添加下载记录
     * 3. 增加课程的下载次数
     *
     * @param userId   用户ID
     * @param courseId 课程ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void downloadCourse(Integer userId, Integer courseId) {
        // 1. 查询用户是否已下载该课程
        CourseDownload download = courseDownloadMapper.selectByUserAndCourse(userId, courseId);

        if (download == null) {
            // 2. 未下载，添加下载记录
            download = new CourseDownload();
            download.setUserId(userId);
            download.setCourseId(courseId);
            download.setCreateTime(LocalDateTime.now());
            courseDownloadMapper.insert(download);

            // 3. 增加课程的下载次数
            baseMapper.incrementDownloadCount(courseId);
        }
        // 如果已下载，不做任何操作（防止重复记录下载次数）
    }

    /**
     * 增加课程观看次数
     * 用于记录课程的观看量
     *
     * @param courseId 课程ID
     */
    @Override
    public void incrementViewCount(Integer courseId) {
        // 调用 Mapper 方法增加观看次数
        baseMapper.incrementViewCount(courseId);
    }
}
