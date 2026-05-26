package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Banner;
import com.sunmao.ljx.mapper.BannerMapper;
import com.sunmao.ljx.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图服务实现类
 * 作用：实现轮播图相关的业务逻辑
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
 * 继承 ServiceImpl<BannerMapper, Banner> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 BannerService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    /**
     * 查询所有启用的轮播图
     * 直接调用 Mapper 层的自定义方法
     *
     * @return 启用的轮播图列表
     */
    @Override
    public List<Banner> getActiveList() {
        // 调用 BannerMapper 的自定义方法查询启用的轮播图
        return baseMapper.selectActiveList();
    }
}
