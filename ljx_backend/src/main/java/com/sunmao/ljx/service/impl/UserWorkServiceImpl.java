package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.UserWork;
import com.sunmao.ljx.mapper.UserWorkMapper;
import com.sunmao.ljx.service.UserWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户作品服务实现类
 * 作用：实现用户作品相关的业务逻辑
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
 * 继承 ServiceImpl<UserWorkMapper, UserWork> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 UserWorkService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class UserWorkServiceImpl extends ServiceImpl<UserWorkMapper, UserWork> implements UserWorkService {

    /**
     * 根据用户ID查询作品列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param userId 用户ID
     * @return 该用户的作品列表
     */
    @Override
    public List<UserWork> getListByUserId(Integer userId) {
        // 调用 UserWorkMapper 的自定义方法查询用户作品
        return baseMapper.selectByUserId(userId);
    }

    /**
     * 发布作品
     * 用户发布新作品
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     *
     * @param userWork 作品信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishWork(UserWork userWork) {
        // 设置作品状态为正常
        userWork.setStatus(1);
        // 设置创建时间
        userWork.setCreateTime(LocalDateTime.now());
        // 保存到数据库
        save(userWork);
    }
}
