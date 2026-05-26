package com.sunmao.ljx.common;

import lombok.Data;

import java.util.List;

/**
 * 分页响应结果封装类
 * 作用：封装分页查询的返回结果，包含总记录数、当前页数据、分页信息等
 *
 * 与 Result 类的区别：
 * - Result 是通用响应封装，用于普通接口返回
 * - PageResult 专门用于分页查询接口，额外包含分页相关的元数据
 *
 * 使用场景：当接口需要返回列表数据且支持分页时使用
 * 例如：文章列表、用户列表、评论列表等
 *
 * @Data 是 Lombok 注解，自动生成 getter、setter、toString、equals、hashCode 方法
 */
@Data
public class PageResult<T> {

    /**
     * 总记录数
     * 表示符合查询条件的所有记录总数，不是当前页的记录数
     * 用于前端计算总页数和显示"共 X 条记录"
     */
    private Long total;

    /**
     * 当前页的数据列表
     * 使用泛型 T，可以承载任意类型的数据列表
     * 例如：List<Article>、List<User> 等
     */
    private List<T> list;

    /**
     * 当前页码
     * 从 1 开始计数，表示当前是第几页
     */
    private Long current;

    /**
     * 每页显示的记录数
     * 表示每一页最多显示多少条数据
     */
    private Long size;

    /**
     * 总页数
     * 根据 total 和 size 计算得出
     * 计算公式：(总记录数 + 每页大小 - 1) / 每页大小
     * 这样计算可以确保向上取整，例如 11 条记录，每页 10 条，总页数为 2
     */
    private Long pages;

    /**
     * 构造方法
     * 创建分页结果对象时传入必要的分页参数
     *
     * @param total   总记录数
     * @param list    当前页数据列表
     * @param current 当前页码
     * @param size    每页记录数
     */
    public PageResult(Long total, List<T> list, Long current, Long size) {
        // 设置总记录数
        this.total = total;
        // 设置当前页数据
        this.list = list;
        // 设置当前页码
        this.current = current;
        // 设置每页大小
        this.size = size;
        // 计算总页数，使用向上取整的算法
        // 例如：total=11, size=10 -> (11+10-1)/10 = 20/10 = 2 页
        this.pages = (total + size - 1) / size;
    }
}
