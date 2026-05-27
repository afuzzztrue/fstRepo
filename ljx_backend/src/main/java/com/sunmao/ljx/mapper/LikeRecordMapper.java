package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.LikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 点赞记录数据访问层
 */
@Mapper
public interface LikeRecordMapper extends BaseMapper<LikeRecord> {

    @Select("SELECT * FROM like_record WHERE user_id = #{userId} AND target_type = #{targetType} AND target_id = #{targetId} LIMIT 1")
    LikeRecord selectByUserAndTarget(@Param("userId") Integer userId,
                                      @Param("targetType") Integer targetType,
                                      @Param("targetId") Integer targetId);
}
