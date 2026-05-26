package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Post;
import com.sunmao.ljx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动态帖子控制器
 * 作用：处理动态帖子相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/post") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/list") 的实际路径是 /api/post/list
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    /**
     * 动态帖子服务
     * 用于调用动态帖子相关的业务逻辑
     *
     * @Autowired 注解说明：
     * - 按类型注入依赖
     * - Spring 会自动查找类型匹配的 Bean 并注入
     * - 适用于注入 Service 接口
     */
    @Autowired
    private PostService postService;

    /**
     * 获取帖子列表
     * 用于动态广场展示帖子列表
     *
     * @GetMapping("/list") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/post/list
     * - 通常用于查询资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/post/list?page=1&size=10
     * - defaultValue 表示默认值
     *
     * @param page 页码，默认为 1
     * @param size 每页记录数，默认为 10
     * @return 帖子列表
     */
    @GetMapping("/list")
    public Result<List<Post>> getPostList(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        // 调用 Service 层的查询方法
        List<Post> list = postService.getPostList(page, size);
        // 返回成功响应，包含帖子列表
        return Result.success(list);
    }

    /**
     * 根据用户ID获取帖子列表
     * 用于用户主页展示该用户发布的帖子
     *
     * @GetMapping("/user/{userId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/post/user/{userId}
     * - {userId} 是路径变量，表示用户ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/post/user/1，userId 的值为 1
     *
     * @param userId 用户ID
     * @return 该用户的帖子列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<Post>> getUserPosts(@PathVariable Integer userId) {
        // 调用 Service 层的查询方法
        List<Post> list = postService.getUserPosts(userId);
        // 返回成功响应，包含帖子列表
        return Result.success(list);
    }

    /**
     * 发布帖子
     * 用户发布新帖子
     *
     * @PostMapping("/publish") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/post/publish
     * - 通常用于创建资源的操作
     *
     * @RequestBody 注解说明：
     * - 将请求体中的 JSON 数据自动转换为 Post 对象
     * - 要求请求头的 Content-Type 为 application/json
     *
     * @param post 帖子信息
     * @return 发布结果
     */
    @PostMapping("/publish")
    public Result<Void> publishPost(@RequestBody Post post) {
        // 调用 Service 层的保存方法
        postService.save(post);
        // 返回成功响应（无数据）
        return Result.success();
    }

    /**
     * 点赞帖子
     * 用户对帖子进行点赞或取消点赞
     *
     * @PostMapping("/like") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/post/like
     * - 通常用于创建或修改资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/post/like?postId=1&userId=2
     *
     * @param postId 帖子ID
     * @param userId 用户ID
     * @return 操作结果
     */
    @PostMapping("/like")
    public Result<Void> likePost(@RequestParam Integer postId, @RequestParam Integer userId) {
        // 调用 Service 层的点赞方法
        postService.likePost(postId, userId);
        // 返回成功响应（无数据）
        return Result.success();
    }
}
