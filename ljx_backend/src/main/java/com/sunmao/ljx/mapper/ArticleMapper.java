package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 文章数据访问层（Mapper）
 * 作用：定义对文章表（article）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Article> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询热门文章列表
     * 用于首页展示热门内容
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM article：查询 article 表的所有字段
     * - WHERE is_hot = 1：筛选热门文章
     * - AND status = 1：筛选已发布的文章
     * - ORDER BY sort_order, create_time DESC：先按排序号升序，再按创建时间降序
     * - LIMIT #{limit}：限制返回的记录数
     *
     * @param limit 返回的记录数限制
     * @return 热门文章列表
     */
    @Select("SELECT * FROM article WHERE is_hot = 1 AND status = 1 ORDER BY sort_order, create_time DESC LIMIT #{limit}")
    List<Article> selectHotList(@Param("limit") Integer limit);

    /**
     * 根据分类ID查询文章列表
     * 用于分类页面展示该分类下的文章
     *
     * SQL 说明：
     * - WHERE category_id = #{categoryId}：筛选指定分类的文章
     * - AND status = 1：筛选已发布的文章
     * - ORDER BY create_time DESC：按创建时间降序排列（最新的在前面）
     *
     * @param categoryId 分类ID
     * @return 该分类下的文章列表
     */
    @Select("SELECT * FROM article WHERE category_id = #{categoryId} AND status = 1 ORDER BY create_time DESC")
    List<Article> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 增加文章浏览次数
     * 用于记录文章的浏览量
     *
     * @Update 注解说明：
     * - 标识这是一个更新操作的 SQL
     * - 执行后会返回受影响的行数
     *
     * SQL 说明：
     * - UPDATE article：更新 article 表
     * - SET view_count = view_count + 1：将浏览次数加 1
     * - WHERE article_id = #{articleId}：指定要更新的文章
     *
     * @param articleId 文章ID
     * @return 受影响的行数
     */
    @Update("UPDATE article SET view_count = view_count + 1 WHERE article_id = #{articleId}")
    int incrementViewCount(@Param("articleId") Integer articleId);

    /**
     * 更新文章点赞次数
     * 用于点赞和取消点赞时更新点赞数
     *
     * SQL 说明：
     * - SET like_count = like_count + #{delta}：将点赞次数增加或减少
     * - delta 为正数表示点赞，为负数表示取消点赞
     *
     * @param articleId 文章ID
     * @param delta     变化量（+1 或 -1）
     * @return 受影响的行数
     */
    @Update("UPDATE article SET like_count = like_count + #{delta} WHERE article_id = #{articleId}")
    int updateLikeCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);

    /**
     * 更新文章收藏次数
     * 用于收藏和取消收藏时更新收藏数
     *
     * SQL 说明：
     * - SET collect_count = collect_count + #{delta}：将收藏次数增加或减少
     * - delta 为正数表示收藏，为负数表示取消收藏
     *
     * @param articleId 文章ID
     * @param delta     变化量（+1 或 -1）
     * @return 受影响的行数
     */
    @Update("UPDATE article SET collect_count = collect_count + #{delta} WHERE article_id = #{articleId}")
    int updateCollectCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);

    /**
     * 更新文章评论次数
     * 用于发表评论和删除评论时更新评论数
     *
     * SQL 说明：
     * - SET comment_count = comment_count + #{delta}：将评论次数增加或减少
     * - delta 为正数表示新增评论，为负数表示删除评论
     *
     * @param articleId 文章ID
     * @param delta     变化量（+1 或 -1）
     * @return 受影响的行数
     */
    @Update("UPDATE article SET comment_count = comment_count + #{delta} WHERE article_id = #{articleId}")
    int updateCommentCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);
}
