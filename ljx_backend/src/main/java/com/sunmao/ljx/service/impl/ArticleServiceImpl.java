package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Article;
import com.sunmao.ljx.entity.LikeRecord;
import com.sunmao.ljx.entity.CollectRecord;
import com.sunmao.ljx.mapper.ArticleMapper;
import com.sunmao.ljx.mapper.LikeRecordMapper;
import com.sunmao.ljx.mapper.CollectRecordMapper;
import com.sunmao.ljx.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章服务实现类
 * 作用：实现文章相关的业务逻辑
 *
 * Service 层是三层架构中的业务逻辑层，负责处理业务规则
 * 主要工作：
 * 1. 接收 Controller 传递的参数
 * 2. 调用 Mapper 层进行数据操作
 * 3. 处理业务逻辑（如校验、计算、转换等）
 * 4. 返回处理结果
 *
 * @Service 注解说明：
 * - 标识这是一个 Spring 的 Service 组件
 * - Spring 会自动扫描并创建该类的 Bean 实例
 * - 可以被 @Autowired 注入到 Controller 中
 *
 * 继承 ServiceImpl<ArticleMapper, Article> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 ArticleService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 点赞记录 Mapper
     * 用于操作点赞记录表
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Mapper 接口
     */
    @Resource
    private LikeRecordMapper likeRecordMapper;

    /**
     * 收藏记录 Mapper
     * 用于操作收藏记录表
     */
    @Resource
    private CollectRecordMapper collectRecordMapper;

    /**
     * 查询热门文章列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param limit 返回的记录数限制
     * @return 热门文章列表
     */
    @Override
    public List<Article> getHotList(Integer limit) {
        // 调用 ArticleMapper 的自定义方法查询热门文章
        return baseMapper.selectHotList(limit);
    }

    /**
     * 根据分类ID查询文章列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    @Override
    public List<Article> getListByCategoryId(Integer categoryId) {
        // 调用 ArticleMapper 的自定义方法查询分类文章
        return baseMapper.selectByCategoryId(categoryId);
    }

    /**
     * 点赞文章
     * 用户点赞文章，如果已点赞则取消点赞
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     * - 保证点赞记录和文章点赞数的一致性
     *
     * 业务逻辑：
     * 1. 查询用户是否已点赞
     * 2. 如果已点赞，删除点赞记录（取消点赞）
     * 3. 如果未点赞，添加点赞记录（点赞）
     * 4. 更新文章的点赞数
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void likeArticle(Integer articleId, Integer userId) {
        // 1. 查询用户是否已点赞
        LikeRecord likeRecord = likeRecordMapper.selectByUserAndTarget(userId, 1, articleId);

        if (likeRecord != null) {
            // 2. 已点赞，删除点赞记录（取消点赞）
            likeRecordMapper.deleteById(likeRecord.getLikeId());
            // 更新文章点赞数（减1）
            baseMapper.updateLikeCount(articleId, -1);
        } else {
            // 3. 未点赞，添加点赞记录（点赞）
            likeRecord = new LikeRecord();
            likeRecord.setUserId(userId);
            likeRecord.setTargetType(1); // 1 表示文章
            likeRecord.setTargetId(articleId);
            likeRecord.setCreateTime(LocalDateTime.now());
            likeRecordMapper.insert(likeRecord);
            // 更新文章点赞数（加1）
            baseMapper.updateLikeCount(articleId, 1);
        }
    }

    /**
     * 收藏文章
     * 用户收藏文章，如果已收藏则取消收藏
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 保证收藏记录和文章收藏数的一致性
     *
     * 业务逻辑：
     * 1. 查询用户是否已收藏
     * 2. 如果已收藏，删除收藏记录（取消收藏）
     * 3. 如果未收藏，添加收藏记录（收藏）
     * 4. 更新文章的收藏数
     *
     * @param articleId 文章ID
     * @param userId    用户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collectArticle(Integer articleId, Integer userId) {
        // 1. 查询用户是否已收藏
        CollectRecord collectRecord = collectRecordMapper.selectByUserAndArticle(userId, articleId);

        if (collectRecord != null) {
            // 2. 已收藏，删除收藏记录（取消收藏）
            collectRecordMapper.deleteById(collectRecord.getCollectId());
            // 更新文章收藏数（减1）
            baseMapper.updateCollectCount(articleId, -1);
        } else {
            // 3. 未收藏，添加收藏记录（收藏）
            collectRecord = new CollectRecord();
            collectRecord.setUserId(userId);
            collectRecord.setArticleId(articleId);
            collectRecord.setCreateTime(LocalDateTime.now());
            collectRecordMapper.insert(collectRecord);
            // 更新文章收藏数（加1）
            baseMapper.updateCollectCount(articleId, 1);
        }
    }

    /**
     * 增加浏览次数
     * 用于记录文章的浏览量
     *
     * @param articleId 文章ID
     */
    @Override
    public void incrementViewCount(Integer articleId) {
        // 调用 Mapper 方法增加浏览次数
        baseMapper.incrementViewCount(articleId);
    }
}
