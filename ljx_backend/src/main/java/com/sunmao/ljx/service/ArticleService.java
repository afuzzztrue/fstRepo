package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.Article;

import java.util.List;

/**
 * 文章服务接口
 * 功能说明: 定义文章模块的业务逻辑接口，继承MyBatis-Plus的IService获取基础CRUD能力
 * 设计模式: 采用接口+实现类的分层设计，便于解耦和单元测试
 * 接口职责: 声明文章相关的所有业务操作方法，由ArticleServiceImpl提供具体实现
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取热门文章列表
     * 使用场景: 首页热门内容区域展示
     *
     * @param limit 限制返回的记录数量
     * @return 热门文章列表
     */
    List<Article> getHotList(Integer limit);

    /**
     * 根据分类ID获取文章列表
     * 使用场景: 分类页面展示该分类下的所有文章
     *
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    List<Article> getListByCategory(Integer categoryId);

    /**
     * 获取文章详情
     * 使用场景: 文章详情页展示完整内容
     * 附加功能: 自动增加文章浏览次数
     *
     * @param articleId 文章ID
     * @return 文章详情对象
     */
    Article getDetail(Integer articleId);

    /**
     * 增加文章浏览次数
     * 使用场景: 用户查看文章详情时，浏览量+1
     *
     * @param articleId 文章ID
     */
    void incrementViewCount(Integer articleId);

    /**
     * 点赞/取消点赞文章
     * 使用场景: 用户点击文章点赞按钮时切换点赞状态
     * 业务逻辑:
     *   - 如果用户未点赞，则添加点赞记录并增加点赞数
     *   - 如果用户已点赞，则删除点赞记录并减少点赞数
     * 事务控制: 涉及多个数据库操作，需要事务保证数据一致性
     *
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    void toggleLike(Integer articleId, Integer userId);

    /**
     * 收藏/取消收藏文章
     * 使用场景: 用户点击文章收藏按钮时切换收藏状态
     * 业务逻辑:
     *   - 如果用户未收藏，则添加收藏记录并增加收藏数
     *   - 如果用户已收藏，则删除收藏记录并减少收藏数
     * 事务控制: 涉及多个数据库操作，需要事务保证数据一致性
     *
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    void toggleCollect(Integer articleId, Integer userId);
}
