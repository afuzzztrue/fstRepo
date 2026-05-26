package com.sunmao.ljx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用程序启动类
 * 这是整个后端服务的入口，负责启动 Spring Boot 应用并扫描所有组件
 *
 * 注解说明：
 * @SpringBootApplication - Spring Boot 核心组合注解，包含以下三个注解的功能：
 *   1. @Configuration - 标识这是一个配置类
 *   2. @EnableAutoConfiguration - 启用 Spring Boot 的自动配置机制
 *   3. @ComponentScan - 自动扫描当前包及其子包下的所有 Spring 组件（@Controller、@Service、@Repository 等）
 *
 * @MapperScan("com.sunmao.ljx.mapper") - MyBatis 的 Mapper 扫描注解
 *   作用：自动扫描指定包路径下的所有 Mapper 接口，将其注册为 Spring Bean
 *   这样就不需要在每个 Mapper 接口上单独加 @Mapper 注解也能被识别
 *   参数 "com.sunmao.ljx.mapper" 是 Mapper 接口所在的包路径
 */
@SpringBootApplication
@MapperScan("com.sunmao.ljx.mapper")
public class LjxPlatformApplication {

    /**
     * 应用程序主入口方法
     * 运行此类即可启动整个 Spring Boot 服务
     *
     * SpringApplication.run() 方法的作用：
     * 1. 创建 Spring 应用上下文（ApplicationContext）
     * 2. 加载所有配置类和 Bean 定义
     * 3. 启动内嵌的 Tomcat 服务器
     * 4. 将应用部署到 Tomcat 中
     *
     * @param args 命令行参数，可通过 java -jar 命令传入
     */
    public static void main(String[] args) {
        SpringApplication.run(LjxPlatformApplication.class, args);
    }
}
