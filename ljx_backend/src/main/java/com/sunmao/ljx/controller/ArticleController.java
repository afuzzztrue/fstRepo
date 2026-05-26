package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Article;
import com.sunmao.ljx.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章控制器
 * 作用：处理文章相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/article") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/hot") 的实际路径是 /api/article/hot
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    /**
     * 文章服务
     * 用于调用文章相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private ArticleService articleService;

    /**
     * 获取热门文章列表
     * 用于首页展示热门内容
     *
     * @GetMapping("/hot") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/article/hot
     * - 通常用于查询资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/article/hot?limit=5，limit 的值为 5
     * - required = false 表示该参数可选，不传时使用默认值
     * - defaultValue = "5" 表示默认值为 5
     *
     * @param limit 返回的记录数限制，默认为 5
     * @return 热门文章列表
     */
    @GetMapping("/hot")
    public Result<List<Article>> getHotList(@RequestParam(required = false, defaultValue = "5") Integer limit) {
        // 调用 Service 层的查询方法
        List<Article> list = articleService.getHotList(limit);
        // 返回成功响应，包含热门文章列表
        return Result.success(list);
    }

    /**
     * 根据分类ID获取文章列表
     * 用于分类页面展示该分类下的文章
     *
     * @GetMapping("/category/{categoryId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/article/category/{categoryId}
     * - {categoryId} 是路径变量，表示分类ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/article/category/1，categoryId 的值为 1
     *
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Article>> getListByCategoryId(@PathVariable Integer categoryId) {
        // 调用 Service 层的查询方法
        List<Article> list = articleService.getListByCategoryId(categoryId);
        // 返回成功响应，包含文章列表
        return Result.success(list);
    }

    /**
     * 获取文章详情
     * 根据文章ID查询文章详情
     *
     * @GetMapping("/detail/{articleId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/article/detail/{articleId}
     * - {articleId} 是路径变量，表示文章ID
     *
     * @param articleId 文章ID
     * @return 文章详情
     */
    @GetMapping("/detail/{articleId}")
    public Result<Article> getArticleDetail(@PathVariable Integer articleId) {
        // 增加浏览次数
        articleService.incrementViewCount(articleId);
        // 调用 Service 层的查询方法获取文章详情
        Article article = articleService.getById(articleId);
        // 返回成功响应，包含文章详情
        return Result.success(article);
    }

    /**
     * 点赞文章
     * 用户对文章进行点赞或取消点赞
     *
     * @PostMapping("/like") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/article/like
     * - 通常用于创建或修改资源的操作
     *
     * @RequestParam 注解说明：
     * - 从 URL 查询参数中获取值
     * - 例如：/api/article/like?articleId=1&userId=2
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     * @return 操作结果
     */
    @PostMapping("/like")
    public Result<Void> likeArticle(@RequestParam Integer articleId, @RequestParam Integer userId) {
        // 调用 Service 层的点赞方法
        articleService.likeArticle(articleId, userId);
        // 返回成功响应（无数据）
        return Result.success();
    }

    /**
     * 收藏文章
     * 用户对文章进行收藏或取消收藏
     *
     * @PostMapping("/collect") 注解说明：
     * - 处理 POST 请求
     * - 路径为 /api/article/collect
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     * @return 操作结果
     */
    @PostMapping("/collect")
    public Result<Void> collectArticle(@RequestParam Integer articleId, @RequestParam Integer userId) {
        // 调用 Service 层的收藏方法
        articleService.collectArticle(articleId, userId);
        // 返回成功响应（无数据）
        return Result.success();
    }
}
