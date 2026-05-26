package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.Category;

import java.util.List;

/**
 * 分类服务接口
 * 作用：定义分类相关的业务逻辑操作
 *
 * 为什么需要 Service 接口？
 * 1. 面向接口编程：Controller 层依赖接口而非实现类，降低耦合度
 * 2. 便于扩展：可以方便地切换不同的实现
 * 3. 便于测试：可以使用 Mock 对象进行单元测试
 * 4. 符合 Spring 的 AOP 编程思想
 *
 * 继承 IService<Category> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要定义特殊业务需要的自定义方法
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询所有启用的分类
     * 用于分类页面展示所有分类
     *
     * @return 分类列表
     */
    List<Category> getActiveList();

    /**
     * 根据父分类ID查询子分类
     * 用于获取某个分类下的子分类
     *
     * @param parentId 父分类ID，0 表示根分类
     * @return 子分类列表
     */
    List<Category> getByParentId(Integer parentId);
}
