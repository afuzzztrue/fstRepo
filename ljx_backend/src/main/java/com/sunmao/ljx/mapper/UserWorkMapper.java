package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.UserWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户作品数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供UserWork实体的基础CRUD操作
 * 扩展方法: 提供根据用户ID查询作品列表的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface UserWorkMapper extends BaseMapper<UserWork> {

    /**
     * 根据用户ID查询作品列表
     * 使用场景: 用户个人主页的"我的作品"区域展示该用户发布的所有作品
     * SQL说明: 查询user_work表中user_id匹配且status=1（正常状态）的记录，按create_time降序排列
     *
     * @param userId 用户ID
     * @return 该用户发布的作品列表，按时间倒序排列
     */
    @Select("SELECT * FROM user_work WHERE user_id = #{userId} AND status = 1 ORDER BY create_time DESC")
    List<UserWork> selectByUserId(@Param("userId") Integer userId);
}
