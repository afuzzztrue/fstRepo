package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Category;
import com.sunmao.ljx.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类控制器
 * 作用：处理分类相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/category") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/list") 的实际路径是 /api/category/list
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    /**
     * 分类服务
     * 用于调用分类相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类列表
     * 用于分类页面展示所有分类
     *
     * @GetMapping("/list") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/category/list
     * - 通常用于查询资源的操作
     *
     * @return 分类列表
     */
    @GetMapping("/list")
    public Result<List<Category>> getList() {
        // 调用 Service 层的查询方法
        List<Category> list = categoryService.getActiveList();
        // 返回成功响应，包含分类列表
        return Result.success(list);
    }

    /**
     * 根据父分类ID获取子分类
     * 用于获取某个分类下的子分类
     *
     * @GetMapping("/children/{parentId}") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/category/children/{parentId}
     * - {parentId} 是路径变量，表示父分类ID
     *
     * @PathVariable 注解说明：
     * - 从 URL 路径中获取变量值
     * - 例如：/api/category/children/0，parentId 的值为 0
     *
     * @param parentId 父分类ID，0 表示根分类
     * @return 子分类列表
     */
    @GetMapping("/children/{parentId}")
    public Result<List<Category>> getChildren(@PathVariable Integer parentId) {
        // 调用 Service 层的查询方法
        List<Category> list = categoryService.getByParentId(parentId);
        // 返回成功响应，包含子分类列表
        return Result.success(list);
    }
}
