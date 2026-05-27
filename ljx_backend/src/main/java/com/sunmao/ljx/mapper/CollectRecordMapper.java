package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.CollectRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 收藏记录数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供CollectRecord实体的基础CRUD操作
 * 扩展方法: 提供根据用户ID和文章ID查询收藏记录的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface CollectRecordMapper extends BaseMapper<CollectRecord> {

    /**
     * 根据用户ID和文章ID查询收藏记录
     * 使用场景: 判断用户是否已收藏某篇文章，用于收藏/取消收藏的切换逻辑
     * SQL说明: 查询collect_record表中user_id和article_id同时匹配的记录，LIMIT 1确保只返回一条
     *
     * @param userId 用户ID，标识执行收藏操作的用户
     * @param articleId 文章ID，标识被收藏的文章
     * @return 匹配的收藏记录对象，不存在则返回null（表示用户未收藏）
     */
    @Select("SELECT * FROM collect_record WHERE user_id = #{userId} AND article_id = #{articleId} LIMIT 1")
    CollectRecord selectByUserAndArticle(@Param("userId") Integer userId, @Param("articleId") Integer articleId);
}
