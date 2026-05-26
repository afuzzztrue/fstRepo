package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Footprint;
import com.sunmao.ljx.mapper.FootprintMapper;
import com.sunmao.ljx.service.FootprintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 浏览足迹服务实现类
 * 作用：实现浏览足迹相关的业务逻辑
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
 * 继承 ServiceImpl<FootprintMapper, Footprint> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 FootprintService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, Footprint> implements FootprintService {

    /**
     * 查询用户的最近浏览足迹
     * 直接调用 Mapper 层的自定义方法
     *
     * @param userId 用户ID
     * @param limit  返回的记录数限制
     * @return 用户的浏览足迹列表
     */
    @Override
    public List<Footprint> getRecentByUserId(Integer userId, Integer limit) {
        // 调用 FootprintMapper 的自定义方法查询最近足迹
        return baseMapper.selectRecentByUserId(userId, limit);
    }

    /**
     * 添加浏览足迹
     * 记录用户的浏览行为
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFootprint(Integer userId, Integer articleId) {
        // 创建足迹记录
        Footprint footprint = new Footprint();
        footprint.setUserId(userId);
        footprint.setArticleId(articleId);
        footprint.setCreateTime(LocalDateTime.now());
        // 保存到数据库
        save(footprint);
    }
}
