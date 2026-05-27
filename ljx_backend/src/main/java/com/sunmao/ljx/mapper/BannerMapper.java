package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 轮播图数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Banner实体的基础CRUD操作
 * 扩展方法: 提供查询所有启用状态轮播图的方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    /**
     * 查询所有启用状态的轮播图列表
     * 使用场景: 首页加载时获取轮播图数据
     * SQL说明: 查询banner表中status=1（启用状态）的记录，按sort_order升序排列
     * 排序说明: sort_order数值越小越靠前，确保轮播图按指定顺序展示
     *
     * @return 启用状态的轮播图列表，按排序号升序排列
     */
    @Select("SELECT * FROM banner WHERE status = 1 ORDER BY sort_order")
    List<Banner> selectActiveList();
}
