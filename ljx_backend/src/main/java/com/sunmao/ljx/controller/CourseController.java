package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Course;
import com.sunmao.ljx.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教程课程控制器
 * 作用：处理教程课程相关的 HTTP 请求，是前后端交互的入口
 *
 * Controller 层是三层架构中的控制层，负责接收和响应 HTTP 请求
 * 主要工作：
 * 1. 接收前端传递的参数（URL 参数、请求体、路径变量等）
 * 2. 调用 Service 层处理业务逻辑
 * 3. 将处理结果封装为统一的响应格式返回给前端
 * 4. 进行简单的参数校验
 *
 * @RestController 注解说明：
 * - 是 @Controller 和 @ResponseBody 的组合注解
 * - @Controller：标识这是一个 Spring MVC 的控制器类
 * - @ResponseBody：将方法的返回值自动转换为 JSON 格式
 * - 被此注解标记的类中的所有方法都会返回 JSON 数据
 *
 * @RequestMapping("/api/course") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/list") 的实际路径是 /api/course/list
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    /**
     * 教程课程服务
     * 用于调用教程课程相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private CourseService courseService;

    /**
     * 根据分类ID获取课程列表
     * 用于分类页面展示该分类下的课程
     *
     * @GetMapping("/category/{categoryId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/course/category/{categoryId}
     * - {categoryId} 是路径变量，表示分类ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/course/category/5，categoryId 的值为 5
     *
     * @param categoryId 分类ID
     * @return 该分类下的课程列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Course>> getListByCategoryId(@PathVariable Integer categoryId) {
        // 调用 Service 层的查询方法
        List<Course> list = courseService.getListByCategoryId(categoryId);
        // 返回成功响应，包含课程列表
        return Result.success(list);
    }

    /**
     * 根据难度等级获取课程列表
     * 用于按难度筛选课程
     *
     * @GetMapping("/difficulty/{difficulty}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/course/difficulty/{difficulty}
     * - {difficulty} 是路径变量，表示难度等级
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/course/difficulty/1，difficulty 的值为 1
     *
     * @param difficulty 难度等级（1：入门、2：进阶、3：高级、4：大师）
     * @return 该难度下的课程列表
     */
    @GetMapping("/difficulty/{difficulty}")
    public Result<List<Course>> getListByDifficulty(@PathVariable Integer difficulty) {
        // 调用 Service 层的查询方法
        List<Course> list = courseService.getListByDifficulty(difficulty);
        // 返回成功响应，包含课程列表
        return Result.success(list);
    }

    /**
     * 下载课程
     * 用户下载课程
     *
     * @PostMapping("/download") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/course/download
     * - 通常用于创建或修改资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/course/download?userId=1&courseId=2
     *
     * @param userId   用户ID
     * @param courseId 课程ID
     * @return 操作结果
     */
    @PostMapping("/download")
    public Result<Void> downloadCourse(@RequestParam Integer userId, @RequestParam Integer courseId) {
        // 调用 Service 层的下载方法
        courseService.downloadCourse(userId, courseId);
        // 返回成功响应（无数据）
        return Result.success();
    }
}
