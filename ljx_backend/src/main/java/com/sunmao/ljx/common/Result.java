package com.sunmao.ljx.common;

import lombok.Data;

/**
 * 统一响应结果封装类
 * 作用：规范所有 API 接口的返回格式，使前端能够以统一的方式处理响应数据
 *
 * 使用了泛型 T，可以封装任意类型的数据（如 User、List<Article> 等）
 * 这是一个典型的"统一响应体"设计模式，在前后端分离项目中非常常见
 *
 * @Data 是 Lombok 注解，会自动生成 getter、setter、toString、equals、hashCode 方法
 * 这样就不需要在代码中手动编写这些方法，减少样板代码
 */
@Data
public class Result<T> {

    /**
     * 响应状态码
     * 200 表示请求成功
     * 500 表示服务器内部错误
     * 其他自定义状态码可根据业务需求扩展
     */
    private Integer code;

    /**
     * 响应消息
     * 成功时通常为 "success"
     * 失败时为具体的错误描述信息
     */
    private String message;

    /**
     * 响应数据
     * 使用泛型 T，可以承载任意类型的返回数据
     * 例如：User 对象、List<Article> 列表、Map 映射等
     */
    private T data;

    /**
     * 私有构造方法
     * 禁止外部直接 new 创建对象，强制通过静态工厂方法创建
     * 这是工厂方法设计模式的应用，确保对象的创建方式统一可控
     */
    private Result() {
    }

    /**
     * 创建成功响应（带数据）
     * 这是一个泛型静态方法，<T> 表示方法的返回类型是 Result<T>
     *
     * 使用示例：
     * Result<User> result = Result.success(user);
     * Result<List<Article>> result = Result.success(articleList);
     *
     * @param data 要返回的数据，可以是任意类型
     * @param <T>  泛型参数，由调用方传入的数据类型决定
     * @return 封装好的成功响应对象
     */
    public static <T> Result<T> success(T data) {
        // 创建一个新的 Result 对象
        Result<T> result = new Result<>();
        // 设置状态码为 200，表示成功
        result.setCode(200);
        // 设置成功消息
        result.setMessage("success");
        // 设置返回的数据
        result.setData(data);
        return result;
    }

    /**
     * 创建成功响应（不带数据）
     * 适用于只需要返回操作成功状态，不需要返回具体数据的场景
     * 例如：点赞成功、删除成功等操作
     *
     * 内部调用 success(null)，复用已有逻辑
     *
     * @param <T> 泛型参数
     * @return 封装好的成功响应对象，data 字段为 null
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 创建错误响应（默认状态码 500）
     * 适用于业务逻辑出错的情况，如用户不存在、密码错误等
     *
     * 使用示例：
     * return Result.error("用户不存在");
     * return Result.error("密码错误");
     *
     * @param message 错误描述信息
     * @param <T>     泛型参数
     * @return 封装好的错误响应对象
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        // 设置状态码为 500，表示服务器内部错误或业务异常
        result.setCode(500);
        // 设置错误消息
        result.setMessage(message);
        // 错误情况下通常不需要返回数据
        return result;
    }

    /**
     * 创建错误响应（自定义状态码）
     * 适用于需要区分不同错误类型的场景
     * 例如：401 未授权、403 禁止访问、404 资源不存在等
     *
     * 使用示例：
     * return Result.error(401, "未登录");
     * return Result.error(404, "文章不存在");
     *
     * @param code    自定义错误状态码
     * @param message 错误描述信息
     * @param <T>     泛型参数
     * @return 封装好的错误响应对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
