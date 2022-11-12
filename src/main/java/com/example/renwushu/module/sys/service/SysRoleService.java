package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
public interface SysRoleService extends IService<SysRole> {

    LambdaQueryWrapper<SysRole> createQueryWrapper(SysRole param);
}
