package com.sunmao.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunmao.ljx.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 轮播图数据访问层（Mapper）
 * 作用：定义对轮播图表（banner）的数据库操作
 *
 * Mapper 层是三层架构中的数据访问层，负责与数据库直接交互
 * 主要工作：执行 SQL 语句，将数据库记录映射为 Java 对象
 *
 * 继承 BaseMapper<Banner> 的好处：
 * - MyBatis Plus 提供了大量通用的 CRUD 方法
 * - 不需要手写 insert、deleteById、updateById、selectById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 *
 * @Mapper 注解说明：
 * - 标识这是一个 MyBatis 的 Mapper 接口
 * - 虽然在启动类上已经使用了 @MapperScan，但这里保留 @Mapper 是良好的编程习惯
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    /**
     * 查询所有启用的轮播图
     * 用于首页轮播图展示
     *
     * @Select 注解说明：
     * - 直接在方法上编写 SQL 语句
     * - 适用于简单的 SQL 查询
     *
     * SQL 说明：
     * - SELECT * FROM banner：查询 banner 表的所有字段
     * - WHERE status = 1：筛选启用的轮播图
     * - ORDER BY sort_order：按排序号升序排列
     *
     * @return 启用的轮播图列表
     */
    @Select("SELECT * FROM banner WHERE status = 1 ORDER BY sort_order")
    List<Banner> selectActiveList();
}
