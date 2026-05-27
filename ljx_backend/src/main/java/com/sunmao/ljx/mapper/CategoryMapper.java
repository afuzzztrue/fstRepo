package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 分类数据访问层
 * 功能说明: 继承MyBatis-Plus的BaseMapper，提供Category实体的基础CRUD操作
 * 扩展方法: 提供根据父分类ID查询子分类的自定义SQL方法
 * 注解说明:
 *   @Mapper - 标识为MyBatis的Mapper接口
 *   @Select - 使用注解方式编写SQL查询语句
 *   @Param - 为SQL参数命名
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据父分类ID查询子分类列表
     * 使用场景: 分类页面加载时，根据parentId获取该分类下的子分类
     * SQL说明: 查询category表中parent_id匹配且status=1（启用状态）的记录，按sort_order排序
     *
     * @param parentId 父分类ID，0表示查询根分类
     * @return 子分类列表，按sort_order升序排列
     */
    @Select("SELECT * FROM category WHERE parent_id = #{parentId} AND status = 1 ORDER BY sort_order")
    List<Category> selectByParentId(@Param("parentId") Integer parentId);
}
