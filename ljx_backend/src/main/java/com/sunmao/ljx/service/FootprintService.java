package com.sunmao.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunmao.ljx.entity.Footprint;

import java.util.List;

/**
 * 浏览足迹服务接口
 */
public interface FootprintService extends IService<Footprint> {

    List<Footprint> getUserFootprints(Integer userId, Integer limit);

    void addFootprint(Integer userId, Integer articleId);
}
