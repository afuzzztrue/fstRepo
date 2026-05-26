package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.Banner;
import com.sunmao.ljx.service.BannerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图控制器
 * 作用：处理轮播图相关的 HTTP 请求，是前后端交互的入口
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
 * @RequestMapping("/api/banner") 注解说明：
 * - 定义该类中所有方法的请求路径前缀
 * - 例如：@GetMapping("/list") 的实际路径是 /api/banner/list
 * - 这样可以统一管理某个模块的 API 路径
 */
@RestController
@RequestMapping("/api/banner")
public class BannerController {

    /**
     * 轮播图服务
     * 用于调用轮播图相关的业务逻辑
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Service 接口
     */
    @Resource
    private BannerService bannerService;

    /**
     * 获取轮播图列表
     * 用于首页轮播图展示
     *
     * @GetMapping("/list") 注解说明：
     * - 处理 GET 请求
     * - 路径为 /api/banner/list
     * - 通常用于查询资源的操作
     *
     * @return 轮播图列表
     */
    @GetMapping("/list")
    public Result<List<Banner>> getList() {
        // 调用 Service 层的查询方法
        List<Banner> list = bannerService.getActiveList();
        // 返回成功响应，包含轮播图列表
        return Result.success(list);
    }
}
