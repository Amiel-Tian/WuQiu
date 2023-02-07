package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.renwushu.module.sys.entity.SysOrgan;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-05
 */
public interface SysOrganService extends IService<SysOrgan> {
    List<SysOrgan> getNavAll();

    LambdaQueryWrapper<SysOrgan> createQueryWrapper(SysOrgan param);
}
