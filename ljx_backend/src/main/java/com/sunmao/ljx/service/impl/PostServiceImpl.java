package com.sunmao.ljx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunmao.ljx.entity.LikeRecord;
import com.sunmao.ljx.entity.Post;
import com.sunmao.ljx.mapper.LikeRecordMapper;
import com.sunmao.ljx.mapper.PostMapper;
import com.sunmao.ljx.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 动态帖子服务实现类
 * 作用：实现动态帖子相关的业务逻辑
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
 * 继承 ServiceImpl<PostMapper, Post> 的好处：
 * - MyBatis Plus 提供了大量通用的 Service 层方法实现
 * - 不需要手写 save、remove、update、getById 等基础方法
 * - 只需要实现自定义的业务方法
 *
 * 实现 PostService 接口：
 * - 必须实现接口中定义的所有方法
 * - 这是面向接口编程的体现
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    /**
     * 点赞记录 Mapper
     * 用于操作点赞记录表
     *
     * @Resource 注解说明：
     * - 按名称注入依赖
     * - 与 @Autowired 类似，但优先按名称匹配
     * - 适用于注入 Mapper 接口
     */
    @Resource
    private LikeRecordMapper likeRecordMapper;

    /**
     * 分页查询帖子列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param page  页码（从 1 开始）
     * @param limit 每页记录数
     * @return 帖子列表
     */
    @Override
    public List<Post> getPageList(Integer page, Integer limit) {
        // 计算偏移量：跳过的记录数 = (页码 - 1) * 每页大小
        Integer offset = (page - 1) * limit;
        // 调用 PostMapper 的自定义方法查询帖子列表
        return baseMapper.selectPageList(offset, limit);
    }

    /**
     * 根据用户ID查询帖子列表
     * 直接调用 Mapper 层的自定义方法
     *
     * @param userId 用户ID
     * @return 该用户的帖子列表
     */
    @Override
    public List<Post> getListByUserId(Integer userId) {
        // 调用 PostMapper 的自定义方法查询用户帖子
        return baseMapper.selectByUserId(userId);
    }

    /**
     * 点赞帖子
     * 用户点赞帖子，如果已点赞则取消点赞
     *
     * @Transactional 注解说明：
     * - 标识这是一个事务方法
     * - 方法执行过程中如果发生异常，会自动回滚事务
     * - 保证点赞记录和帖子点赞数的一致性
     *
     * 业务逻辑：
     * 1. 查询用户是否已点赞
     * 2. 如果已点赞，删除点赞记录（取消点赞）
     * 3. 如果未点赞，添加点赞记录（点赞）
     * 4. 更新帖子的点赞数
     *
     * @param postId 帖子ID
     * @param userId 用户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void likePost(Integer postId, Integer userId) {
        // 1. 查询用户是否已点赞
        LikeRecord likeRecord = likeRecordMapper.selectByUserAndTarget(userId, 2, postId);

        if (likeRecord != null) {
            // 2. 已点赞，删除点赞记录（取消点赞）
            likeRecordMapper.deleteById(likeRecord.getLikeId());
            // 更新帖子点赞数（减1）
            baseMapper.updateLikeCount(postId, -1);
        } else {
            // 3. 未点赞，添加点赞记录（点赞）
            likeRecord = new LikeRecord();
            likeRecord.setUserId(userId);
            likeRecord.setTargetType(2); // 2 表示帖子
            likeRecord.setTargetId(postId);
            likeRecord.setCreateTime(LocalDateTime.now());
            likeRecordMapper.insert(likeRecord);
            // 更新帖子点赞数（加1）
            baseMapper.updateLikeCount(postId, 1);
        }
    }
}
