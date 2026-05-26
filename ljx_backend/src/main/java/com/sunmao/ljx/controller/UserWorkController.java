package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.UserWork;
import com.sunmao.ljx.service.UserWorkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户作品控制器
 * 作用：处理用户作品相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/work") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/user/{userId}") 的实际路径是 /api/work/user/{userId}
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/work")
public class UserWorkController {

    /**
     * 用户作品服务
     * 用于调用用户作品相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private UserWorkService userWorkService;

    /**
     * 根据用户ID获取作品列表
     * 用于用户主页展示该用户发布的作品
     *
     * @GetMapping("/user/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/work/user/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/work/user/1，userId 的值为 1
     *
     * @param userId 用户ID
     * @return 该用户的作品列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<UserWork>> getListByUserId(@PathVariable Integer userId) {
        // 调用 Service 层的查询方法
        List<UserWork> list = userWorkService.getListByUserId(userId);
        // 返回成功响应，包含作品列表
        return Result.success(list);
    }

    /**
     * 发布作品
     * 用户发布新作品
     *
     * @PostMapping("/publish") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/work/publish
     * - 通常用于创建资源的操作
     *
     * @RequestBody 注解说明：
     * - 将请求体中的 JSON 数据自动转换为 UserWork 对象
     * - 要求请求头的 Content-Type 为 application/json
     *
     * @param userWork 作品信息
     * @return 发布结果
     */
    @PostMapping("/publish")
    public Result<Void> publish(@RequestBody UserWork userWork) {
        // 调用 Service 层的发布方法
        userWorkService.publishWork(userWork);
        // 返回成功响应（无数据）
        return Result.success();
    }
}
