package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 文章数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Article实体的基础CRUD操作
 * 扩展方法: 提供热门文章查询、分类文章查询、浏览量/点赞数/收藏数/评论数更新等操作
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Update - 使用注解方式编写SQL更新语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询热门文章列表
     * 使用场景: 首页热门内容区域展示
     * SQL说明: 查询is_hot=1且status=1的文章，按sort_order和create_time降序排列，限制返回条数
     *
     * @param limit 限制返回的记录数量，如传入5表示只返回前5条
     * @return 热门文章列表
     */
    @Select("SELECT * FROM article WHERE is_hot = 1 AND status = 1 ORDER BY sort_order, create_time DESC LIMIT #{limit}")
    List<Article> selectHotList(@Param("limit") Integer limit);

    /**
     * 根据分类ID查询文章列表
     * 使用场景: 分类页面点击某个分类后展示该分类下的文章
     * SQL说明: 查询category_id匹配且status=1的文章，按create_time降序排列（最新的在前）
     *
     * @param categoryId 分类ID，对应category表的category_id
     * @return 该分类下的文章列表
     */
    @Select("SELECT * FROM article WHERE category_id = #{categoryId} AND status = 1 ORDER BY create_time DESC")
    List<Article> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 增加文章浏览次数
     * 使用场景: 用户查看文章详情时，浏览量+1
     * SQL说明: 使用UPDATE语句将view_count字段值加1，WHERE条件确保只更新指定文章
     * 返回值: 受影响的行数，正常为1
     *
     * @param articleId 文章ID
     * @return 更新的行数
     */
    @Update("UPDATE article SET view_count = view_count + 1 WHERE article_id = #{articleId}")
    int incrementViewCount(@Param("articleId") Integer articleId);

    /**
     * 更新文章点赞数
     * 使用场景: 用户点赞或取消点赞时，更新点赞数量
     * SQL说明: 使用UPDATE语句将like_count字段增加或减少指定数值
     * 参数说明: delta为正数表示点赞（+1），为负数表示取消点赞（-1）
     *
     * @param articleId 文章ID
     * @param delta 点赞数变化量，+1或-1
     * @return 更新的行数
     */
    @Update("UPDATE article SET like_count = like_count + #{delta} WHERE article_id = #{articleId}")
    int updateLikeCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);

    /**
     * 更新文章收藏数
     * 使用场景: 用户收藏或取消收藏时，更新收藏数量
     * SQL说明: 使用UPDATE语句将collect_count字段增加或减少指定数值
     * 参数说明: delta为正数表示收藏（+1），为负数表示取消收藏（-1）
     *
     * @param articleId 文章ID
     * @param delta 收藏数变化量，+1或-1
     * @return 更新的行数
     */
    @Update("UPDATE article SET collect_count = collect_count + #{delta} WHERE article_id = #{articleId}")
    int updateCollectCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);

    /**
     * 更新文章评论数
     * 使用场景: 用户发表评论或删除评论时，更新评论数量
     * SQL说明: 使用UPDATE语句将comment_count字段增加或减少指定数值
     * 参数说明: delta为正数表示新增评论（+1），为负数表示删除评论（-1）
     *
     * @param articleId 文章ID
     * @param delta 评论数变化量，+1或-1
     * @return 更新的行数
     */
    @Update("UPDATE article SET comment_count = comment_count + #{delta} WHERE article_id = #{articleId}")
    int updateCommentCount(@Param("articleId") Integer articleId, @Param("delta") Integer delta);
}
