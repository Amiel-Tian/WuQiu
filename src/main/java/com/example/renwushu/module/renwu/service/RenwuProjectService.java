package com.example.renwushu.module.renwu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.renwu.entity.RenwuProject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-13
 */
public interface RenwuProjectService extends IService<RenwuProject> {

    LambdaQueryWrapper<RenwuProject> createQueryWrapper(RenwuProject param);
}
