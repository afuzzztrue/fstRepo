package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Footprint;
import com.sunmao.ljx.service.FootprintService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 浏览足迹控制器
 * 作用：处理浏览足迹相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/footprint") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/user/{userId}") 的实际路径是 /api/footprint/user/{userId}
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/footprint")
public class FootprintController {

    /**
     * 浏览足迹服务
     * 用于调用浏览足迹相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private FootprintService footprintService;

    /**
     * 获取用户的最近浏览足迹
     * 用于"我的足迹"功能展示
     *
     * @GetMapping("/user/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/footprint/user/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/footprint/user/1，userId 的值为 1
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/footprint/user/1?limit=10
     * - required = false 表示该参数可选，不传时使用默认值
     * - defaultValue = "10" 表示默认值为 10
     *
     * @param userId 用户ID
     * @param limit  返回的记录数限制，默认为 10
     * @return 用户的浏览足迹列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<Footprint>> getRecentByUserId(@PathVariable Integer userId,
                                                      @RequestParam(required = false, defaultValue = "10") Integer limit) {
        // 调用 Service 层的查询方法
        List<Footprint> list = footprintService.getRecentByUserId(userId, limit);
        // 返回成功响应，包含足迹列表
        return Result.success(list);
    }

    /**
     * 添加浏览足迹
     * 记录用户的浏览行为
     *
     * @PostMapping("/add") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/footprint/add
     * - 通常用于创建资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/footprint/add?userId=1&articleId=2
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return 操作结果
     */
    @PostMapping("/add")
    public Result<Void> addFootprint(@RequestParam Integer userId, @RequestParam Integer articleId) {
        // 调用 Service 层的添加方法
        footprintService.addFootprint(userId, articleId);
        // 返回成功响应（无数据）
        return Result.success();
    }
}
