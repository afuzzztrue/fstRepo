package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Category;
import com.sunmao.ljx.mapper.CategoryMapper;
import com.sunmao.ljx.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类服务实现类
 * 作用：实现分类相关的业务逻辑
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
 * 继承 ServiceImpl<CategoryMapper, Category> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 CategoryService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 查询所有启用的分类
     * 使用 MyBatis Plus 的 LambdaQueryWrapper 构建查询条件
     *
     * LambdaQueryWrapper 说明：
     * - MyBatis Plus 提供的条件构造器
     * - 使用 Lambda 表达式构建查询条件，类型安全
     * - 避免了硬编码字段名，编译期即可发现错误
     *
     * @return 分类列表
     */
    @Override
    public List<Category> getActiveList() {
        // 创建 LambdaQueryWrapper 对象
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件：status = 1（启用的分类）
        wrapper.eq(Category::getStatus, 1);
        // 添加排序条件：按 sort_order 升序排列
        wrapper.orderByAsc(Category::getSortOrder);
        // 执行查询并返回结果
        return list(wrapper);
    }

    /**
     * 根据父分类ID查询子分类
     * 直接调用 Mapper 层的自定义方法
     *
     * @param parentId 父分类ID，0 表示根分类
     * @return 子分类列表
     */
    @Override
    public List<Category> getByParentId(Integer parentId) {
        // 调用 CategoryMapper 的自定义方法查询子分类
        return baseMapper.selectByParentId(parentId);
    }
}
