package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.Follow;
import com.sunmao.ljx.mapper.FollowMapper;
import com.sunmao.ljx.service.FollowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 关注关系服务实现类
 * 作用：实现关注关系相关的业务逻辑
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
 * 继承 ServiceImpl<FollowMapper, Follow> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 FollowService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    /**
     * 关注/取消关注
     * 用户关注另一个用户，如果已关注则取消关注
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     * - 保证关注关系的一致性
     *
     * 业务逻辑：
     * 1. 查询是否已关注
     * 2. 如果已关注，删除关注记录（取消关注）
     * 3. 如果未关注，添加关注记录（关注）
     *
     * @param userId       关注者用户ID
     * @param followUserId 被关注者用户ID
     * @return true：关注成功，false：取消关注成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toggleFollow(Integer userId, Integer followUserId) {
        // 1. 查询是否已关注
        Follow follow = baseMapper.selectByUserAndTarget(userId, followUserId);

        if (follow != null) {
            // 2. 已关注，删除关注记录（取消关注）
            removeById(follow.getFollowId());
            return false;
        } else {
            // 3. 未关注，添加关注记录（关注）
            follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowUserId(followUserId);
            follow.setCreateTime(LocalDateTime.now());
            save(follow);
            return true;
        }
    }

    /**
     * 查询用户的关注数量
     * 直接调用 Mapper 层的自定义方法
     *
     * @param userId 用户ID
     * @return 关注数量
     */
    @Override
    public Integer getFollowCount(Integer userId) {
        // 调用 FollowMapper 的自定义方法查询关注数量
        return baseMapper.selectFollowCount(userId);
    }

    /**
     * 查询用户的粉丝数量
     * 直接调用 Mapper 层的自定义方法
     *
     * @param userId 用户ID
     * @return 粉丝数量
     */
    @Override
    public Integer getFollowerCount(Integer userId) {
        // 调用 FollowMapper 的自定义方法查询粉丝数量
        return baseMapper.selectFollowerCount(userId);
    }
}
