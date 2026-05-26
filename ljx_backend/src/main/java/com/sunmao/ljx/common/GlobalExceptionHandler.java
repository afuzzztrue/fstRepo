package com.sunmao.ljx.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 作用：统一捕获和处理整个应用中抛出的异常，避免异常信息直接暴露给前端
 *
 * 为什么需要全局异常处理？
 * 1. 统一响应格式：所有异常都转换为统一的 Result 格式返回
 * 2. 隐藏敏感信息：避免将数据库异常、堆栈信息等暴露给前端
 * 3. 简化 Controller 代码：Controller 中不需要写 try-catch 块
 * 4. 集中管理异常：所有异常处理逻辑集中在一处，便于维护
 *
 * @RestControllerAdvice 注解说明：
 * - 这是 @ControllerAdvice 和 @ResponseBody 的组合注解
 * - @ControllerAdvice：标识这是一个全局控制器通知类，可以拦截所有 Controller 的异常
 * - @ResponseBody：将返回值自动转换为 JSON 格式
 * - 被此注解标记的类中的方法可以全局拦截 Controller 层抛出的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * 当 Controller 中抛出 BusinessException 时，此方法会被自动调用
     *
     * @ExceptionHandler 注解说明：
     * - 指定要处理的异常类型
     * - 当应用中抛出该类型的异常时，Spring 会自动调用此方法
     * - 可以指定多个异常类型：@ExceptionHandler({BusinessException.class, OtherException.class})
     *
     * 处理逻辑：
     * 1. 捕获 BusinessException
     * 2. 从异常对象中获取错误码和错误消息
     * 3. 封装为统一的 Result 对象返回
     *
     * @param e 捕获到的 BusinessException 对象
     * @return 统一格式的错误响应
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        // 使用异常中的错误码和错误消息创建错误响应
        // e.getCode() 获取业务异常的状态码
        // e.getMessage() 获取业务异常的错误描述
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理所有其他异常
     * 当 Controller 中抛出除 BusinessException 之外的所有异常时，此方法会被调用
     * 这是一个兜底的异常处理方法
     *
     * 处理逻辑：
     * 1. 捕获所有未被专门处理的异常
     * 2. 将异常信息包装为友好的提示语
     * 3. 返回统一的错误响应
     *
     * 注意：在生产环境中，不应该将 e.getMessage() 直接返回给前端
     * 应该记录日志，并返回"系统繁忙，请稍后再试"等模糊提示
     *
     * @param e 捕获到的 Exception 对象
     * @return 统一格式的错误响应
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 将异常消息包装为错误响应
        // 实际项目中应该记录日志：log.error("系统异常", e);
        return Result.error("系统异常: " + e.getMessage());
    }
}
