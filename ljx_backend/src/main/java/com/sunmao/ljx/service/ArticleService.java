package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.Article;

import java.util.List;

/**
 * 文章服务接口
 * 作用：定义文章相关的业务逻辑操作
 *
 * 为什么需要 Service 接口？
 * 1. 面向接口编程：Controller 层依赖接口而非实现类，降低耦合度
 * 2. 便于扩展：可以方便地切换不同的实现
 * 3. 便于测试：可以使用 Mock 对象进行单元测试
 * 4. 符合 Spring 的 AOP 编程思想
 *
 * 继承 IService<Article> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询热门文章列表
     * 用于首页展示热门内容
     *
     * @param limit 返回的记录数限制
     * @return 热门文章列表
     */
    List<Article> getHotList(Integer limit);

    /**
     * 根据分类ID查询文章列表
     * 用于分类页面展示该分类下的文章
     *
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    List<Article> getListByCategoryId(Integer categoryId);

    /**
     * 点赞文章
     * 用户点赞文章，如果已点赞则取消点赞
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     */
    void likeArticle(Integer articleId, Integer userId);

    /**
     * 收藏文章
     * 用户收藏文章，如果已收藏则取消收藏
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     */
    void collectArticle(Integer articleId, Integer userId);

    /**
     * 增加浏览次数
     * 用于记录文章的浏览量
     *
     * @param articleId 文章ID
     */
    void incrementViewCount(Integer articleId);
}
