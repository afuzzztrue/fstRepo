package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 关注关系控制器
 * 作用：处理关注关系相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/follow") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@PostMapping("/toggle") 的实际路径是 /api/follow/toggle
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    /**
     * 关注关系服务
     * 用于调用关注关系相关的业务逻辑
     *
     * @Autowired 注解说明：
     * - 按类型注入依赖
     * - Spring 会自动查找类型匹配的 Bean 并注入
     * - 适用于注入 Service 接口
     */
    @Autowired
    private FollowService followService;

    /**
     * 关注/取消关注
     * 用户关注或取消关注另一个用户
     *
     * @PostMapping("/toggle") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/follow/toggle
     * - 通常用于创建或修改资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/follow/toggle?userId=1&followUserId=2
     *
     * @param userId       关注者用户ID
     * @param followUserId 被关注者用户ID
     * @return 操作结果，包含是否关注成功
     */
    @PostMapping("/toggle")
    public Result<Map<String, Object>> toggleFollow(@RequestParam Integer userId, @RequestParam Integer followUserId) {
        // 调用 Service 层的关注/取消关注方法
        boolean isFollowed = followService.toggleFollow(userId, followUserId);
        // 创建响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("isFollowed", isFollowed);
        // 返回成功响应，包含关注状态
        return Result.success(data);
    }

    /**
     * 获取关注统计
     * 查询用户的关注数和粉丝数
     *
     * @GetMapping("/count/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/follow/count/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/follow/count/1，userId 的值为 1
     *
     * @param userId 用户ID
     * @return 关注统计信息
     */
    @GetMapping("/count/{userId}")
    public Result<Map<String, Object>> getFollowCount(@PathVariable Integer userId) {
        // 调用 Service 层的查询方法
        Integer followCount = followService.getFollowCount(userId);
        Integer followerCount = followService.getFollowerCount(userId);
        // 创建响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("followCount", followCount);
        data.put("followerCount", followerCount);
        // 返回成功响应，包含关注统计
        return Result.success(data);
    }

    /**
     * 检查关注状态
     * 查询用户A是否关注了用户B
     *
     * @GetMapping("/check") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/follow/check
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/follow/check?userId=1&followUserId=2
     *
     * @param userId       关注者用户ID
     * @param followUserId 被关注者用户ID
     * @return 关注状态
     */
    @GetMapping("/check")
    public Result<Map<String, Object>> checkFollow(@RequestParam Integer userId, @RequestParam Integer followUserId) {
        // 调用 Service 层的查询方法
        boolean isFollowed = followService.checkFollow(userId, followUserId);
        // 创建响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("isFollowed", isFollowed);
        // 返回成功响应，包含关注状态
        return Result.success(data);
    }
}
