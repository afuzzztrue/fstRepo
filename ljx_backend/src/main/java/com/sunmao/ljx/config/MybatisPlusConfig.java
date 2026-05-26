package com.sunmao.ljx.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus 配置类
 * 作用：配置 MyBatis Plus 的扩展功能，如分页插件
 *
 * MyBatis Plus 是什么？
 * - MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变
 * - 提供了通用的 CRUD 操作、分页插件、代码生成器等功能
 * - 可以大大减少手写 SQL 的工作量
 *
 * @Configuration 注解说明：
 * - 标识这是一个 Spring 配置类
 * - Spring 会在启动时自动加载并应用此类中的配置
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置 MyBatis Plus 拦截器
     * 作用：添加分页插件等内置拦截器
     *
     * @Bean 注解说明：
     * - 将方法的返回值注册为 Spring 容器中的一个 Bean
     * - 其他组件可以通过 @Autowired 注入使用
     * - 相当于在 XML 中配置 <bean id="mybatisPlusInterceptor" class="..."/>
     *
     * MybatisPlusInterceptor 的作用：
     * - 是 MyBatis Plus 的核心拦截器
     * - 可以添加多个内部拦截器（InnerInterceptor）
     * - 每个内部拦截器负责一种功能，如分页、乐观锁、多租户等
     *
     * @return 配置好的 MybatisPlusInterceptor 对象
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建 MyBatis Plus 拦截器实例
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 添加分页内部拦截器
        // PaginationInnerInterceptor 是 MyBatis Plus 提供的分页插件
        // 作用：自动处理分页查询，将物理分页转换为 SQL 的 LIMIT 语句
        // DbType.MYSQL：指定数据库类型为 MySQL，不同数据库的分页语法不同
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 返回配置好的拦截器
        return interceptor;
    }
}
