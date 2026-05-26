package com.sunmao.ljx.common;

/**
 * 业务异常类
 * 作用：封装业务逻辑层面的异常，与系统异常区分开
 *
 * 为什么需要自定义异常？
 * 1. 区分异常类型：业务异常（如用户不存在）vs 系统异常（如数据库连接失败）
 * 2. 统一异常处理：可以通过 GlobalExceptionHandler 统一捕获并处理
 * 3. 友好的错误提示：业务异常可以返回给前端友好的提示信息
 * 4. 事务回滚：配合 @Transactional 注解，发生业务异常时自动回滚事务
 *
 * 继承 RuntimeException 的原因：
 * - RuntimeException 是运行时异常，不需要在方法签名中声明 throws
 * - 业务异常通常是不可恢复的，直接抛出即可
 * - Spring 的 @Transactional 默认只对 RuntimeException 进行回滚
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常状态码
     * 用于区分不同类型的业务错误
     * 例如：500 通用业务错误、1001 用户不存在、1002 密码错误等
     */
    private Integer code;

    /**
     * 构造方法（默认状态码 500）
     * 适用于一般的业务错误，不需要区分具体错误类型
     *
     * 使用示例：
     * throw new BusinessException("用户不存在");
     * throw new BusinessException("密码错误");
     *
     * @param message 错误描述信息
     */
    public BusinessException(String message) {
        // 调用父类 RuntimeException 的构造方法，设置异常消息
        super(message);
        // 设置默认状态码为 500
        this.code = 500;
    }

    /**
     * 构造方法（自定义状态码）
     * 适用于需要区分错误类型的场景
     *
     * 使用示例：
     * throw new BusinessException(1001, "用户不存在");
     * throw new BusinessException(1002, "密码错误");
     *
     * @param code    自定义错误状态码
     * @param message 错误描述信息
     */
    public BusinessException(Integer code, String message) {
        // 调用父类 RuntimeException 的构造方法
        super(message);
        // 设置自定义状态码
        this.code = code;
    }

    /**
     * 获取异常状态码
     *
     * @return 异常状态码
     */
    public Integer getCode() {
        return code;
    }
}
