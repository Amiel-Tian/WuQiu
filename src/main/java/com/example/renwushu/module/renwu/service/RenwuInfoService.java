package com.example.renwushu.module.renwu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.renwu.entity.RenwuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-03
 */
public interface RenwuInfoService extends IService<RenwuInfo> {

    LambdaQueryWrapper<RenwuInfo> createQueryWrapper(RenwuInfo param);
}
