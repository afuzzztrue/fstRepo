package com.sunmao.ljx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置类
 * 作用：解决前后端分离项目中的跨域访问问题
 *
 * 什么是跨域（CORS）？
 * - 浏览器的安全策略，限制从一个源加载的文档或脚本如何与来自另一个源的资源进行交互
 * - 协议不同（http vs https）、域名不同、端口不同，都属于不同源
 * - 例如：前端运行在 http://localhost:3000，后端运行在 http://localhost:8081，这就是跨域
 *
 * 为什么需要配置跨域？
 * - 本项目是微信小程序 + Spring Boot 后端，小程序请求后端时会产生跨域
 * - 如果不配置跨域，浏览器会阻止前端请求，导致接口调用失败
 *
 * @Configuration 注解说明：
 * - 标识这是一个 Spring 配置类
 * - Spring 会在启动时自动加载并应用此类中的配置
 * - 类似于 XML 配置文件中的 <beans> 标签
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 配置跨域映射规则
     * 此方法来自 WebMvcConfigurer 接口，用于自定义 MVC 配置
     *
     * @param registry 跨域注册器，用于添加跨域规则
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // addMapping("/**")：配置跨域规则适用的路径
        // "/**" 表示应用于所有路径，即所有接口都允许跨域访问
        registry.addMapping("/**")
                // allowedOriginPatterns("*")：允许哪些来源（域名）访问
                // "*" 表示允许所有来源，开发环境可以这样配置
                // 生产环境应该指定具体的域名，如 "https://www.example.com"
                .allowedOriginPatterns("*")
                // allowedMethods：允许的 HTTP 请求方法
                // 这里配置了 GET、POST、PUT、DELETE、OPTIONS 五种常用方法
                // OPTIONS 是预检请求方法，浏览器在发送实际请求前会先发送 OPTIONS 请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // allowedHeaders("*")：允许的请求头
                // "*" 表示允许所有请求头，如 Content-Type、Authorization 等
                .allowedHeaders("*")
                // allowCredentials(true)：是否允许携带凭证（如 Cookie）
                // true 表示允许，这样前端可以携带登录态等信息
                .allowCredentials(true)
                // maxAge(3600)：预检请求的缓存时间（秒）
                // 3600 秒 = 1 小时，表示浏览器在 1 小时内不需要重复发送预检请求
                .maxAge(3600);
    }
}
