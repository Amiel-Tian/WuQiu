package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.dao.SysRoleMapper;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public LambdaQueryWrapper<SysRole> createQueryWrapper(SysRole param){
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getName())) {
            queryWrapper.like(SysRole::getName, param.getName());
        }
        if (StringUtils.isNotBlank(param.getCode())) {
            queryWrapper.like(SysRole::getCode, param.getCode());
        }

        if (param.getStatus() != null) {
            queryWrapper.eq(SysRole::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(SysRole::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotBlank(param.getOrderBy())) {
            if (StringUtils.isNotBlank(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysRole::getStatus);
            } else {
                queryWrapper.orderByDesc(SysRole::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysRole::getCreatedTime);
        }
        return queryWrapper;
    }
}
