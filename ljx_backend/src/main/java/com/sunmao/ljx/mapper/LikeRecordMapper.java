package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.LikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 点赞记录数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供LikeRecord实体的基础CRUD操作
 * 扩展方法: 提供根据用户ID、目标类型、目标ID查询点赞记录的方法
 * 设计说明: 通过target_type和target_id实现多态关联，可同时支持文章点赞和帖子点赞
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface LikeRecordMapper extends BaseMapper<LikeRecord> {

    /**
     * 根据用户ID、目标类型、目标ID查询点赞记录
     * 使用场景: 判断用户是否已对某篇文章或帖子点赞，用于点赞/取消点赞的切换逻辑
     * SQL说明: 查询like_record表中user_id、target_type、target_id同时匹配的记录，LIMIT 1确保只返回一条
     * 多态关联说明:
     *   target_type=1时表示查询文章点赞，target_id对应article表的article_id
     *   target_type=2时表示查询帖子点赞，target_id对应post表的post_id
     *
     * @param userId 用户ID，标识执行点赞操作的用户
     * @param targetType 点赞目标类型，1表示文章，2表示帖子
     * @param targetId 点赞目标ID，对应被点赞的文章ID或帖子ID
     * @return 匹配的点赞记录对象，不存在则返回null（表示用户未点赞）
     */
    @Select("SELECT * FROM like_record WHERE user_id = #{userId} AND target_type = #{targetType} AND target_id = #{targetId} LIMIT 1")
    LikeRecord selectByUserAndTarget(@Param("userId") Integer userId,
                                      @Param("targetType") Integer targetType,
                                      @Param("targetId") Integer targetId);
}
