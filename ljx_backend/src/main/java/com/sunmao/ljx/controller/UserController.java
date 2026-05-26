package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.User;
import com.sunmao.ljx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 * 作用：处理用户相关的 HTTP 请求，是前后端交互的入口
 *
 * Controller 层是三层架构中的控制层，负责接收和响应 HTTP 请求
 * 主要工作：
 * 1. 接收前端传递的参数（URL 参数、请求体、路径变量等）
 * 2. 调用 Service 层处理业务逻辑
 * 3. 将处理结果封装为统一的响应格式返回给前端
 * 4. 进行简单的参数校验
 *
 * 为什么不直接在 Controller 中写业务逻辑？
 * 1. 职责分离：Controller 只负责请求响应，Service 负责业务逻辑
 * 2. 便于复用：Service 层的方法可以被多个 Controller 调用
 * 3. 便于测试：可以单独测试 Service 层的业务逻辑
 * 4. 便于维护：业务逻辑变更时只需要修改 Service 层
 *
 * @RestController 注解说明：
 * - 是 @Controller 和 @ResponseBody 的组合注解
 * - @Controller：标识这是一个 Spring MVC 的控制器类
 * - @ResponseBody：将方法的返回值自动转换为 JSON 格式
 * - 被此注解标记的类中的所有方法都会返回 JSON 数据
 *
 * @RequestMapping("/api/user") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/info") 的实际路径是 /api/user/info
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 用户服务
     * 用于调用用户相关的业务逻辑
     *
     * @Autowired 注解说明：
     * - 按类型注入依赖
     * - Spring 会自动查找类型匹配的 Bean 并注入
     * - 适用于注入 Service 接口
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * 接收前端提交的注册信息，调用 Service 层完成注册
     *
     * @PostMapping("/register") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/user/register
     * - 通常用于创建资源的操作
     *
     * @RequestBody 注解说明：
     * - 将请求体中的 JSON 数据自动转换为 User 对象
     * - 要求请求头的 Content-Type 为 application/json
     *
     * @param user 用户信息（包含手机号/邮箱、密码、昵称等）
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        // 调用 Service 层的注册方法
        User registeredUser = userService.register(user.getPhone(), user.getEmail(), user.getPassword(), user.getNickname());
        // 返回成功响应，包含注册成功的用户信息
        return Result.success(registeredUser);
    }

    /**
     * 用户登录
     * 接收前端提交的登录信息，调用 Service 层完成登录
     *
     * @PostMapping("/login") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/user/login
     *
     * @param user 用户信息（包含账号和密码）
     * @return 登录结果，包含用户信息
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        // 判断账号类型（手机号或邮箱）
        String account = user.getPhone() != null ? user.getPhone() : user.getEmail();
        // 调用 Service 层的登录方法
        User loginUser = userService.login(account, user.getPassword());
        // 返回成功响应，包含登录成功的用户信息
        return Result.success(loginUser);
    }

    /**
     * 微信登录
     * 接收前端提交的微信登录信息，调用 Service 层完成登录
     *
     * @PostMapping("/wxLogin") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/user/wxLogin
     *
     * @param user 用户信息（包含 openid、unionid、昵称、头像等）
     * @return 登录结果，包含用户信息
     */
    @PostMapping("/wxLogin")
    public Result<User> wxLogin(@RequestBody User user) {
        // 调用 Service 层的微信登录方法
        User loginUser = userService.wxLogin(user.getOpenid(), user.getUnionid(), user.getNickname(), user.getAvatar());
        // 返回成功响应，包含登录成功的用户信息
        return Result.success(loginUser);
    }

    /**
     * 获取用户信息
     * 根据用户ID查询用户详情
     *
     * @GetMapping("/info/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/user/info/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/user/info/1，userId 的值为 1
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/info/{userId}")
    public Result<User> getUserInfo(@PathVariable Integer userId) {
        // 调用 Service 层的查询方法
        User user = userService.getById(userId);
        // 返回成功响应，包含用户信息
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * 接收前端提交的用户信息更新请求
     *
     * @PostMapping("/update") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/user/update
     *
     * @param user 用户信息（包含要更新的字段）
     * @return 更新结果
     */
    @PostMapping("/update")
    public Result<Void> updateUser(@RequestBody User user) {
        // 调用 Service 层的更新方法
        userService.updateById(user);
        // 返回成功响应（无数据）
        return Result.success();
    }

    /**
     * 获取用户统计信息
     * 查询用户的学习时长等统计数据
     *
     * @GetMapping("/stats/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/user/stats/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/user/stats/1，userId 的值为 1
     *
     * @param userId 用户ID
     * @return 用户统计信息
     */
    @GetMapping("/stats/{userId}")
    public Result<Map<String, Object>> getUserStats(@PathVariable Integer userId) {
        // 调用 Service 层的查询方法
        User user = userService.getById(userId);
        // 创建响应数据
        Map<String, Object> stats = new HashMap<>();
        stats.put("studyHours", user.getStudyHours());
        // 返回成功响应，包含统计信息
        return Result.success(stats);
    }
}
