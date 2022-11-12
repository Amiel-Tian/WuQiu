package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
public interface SysUserService extends IService<SysUser> {
    SysUser getLoginUser();

    public SysUser getByUser(SysUser sysUser);


    String getAuthorityByUser(String userId);

    // 删除某个用户的权限信息
    void clearUserAuthorityInfo(String loginname);

    // 删除所有与该角色关联的用户的权限信息
    void clearUserAuthorityInfoByRoleId(String roleId);

    // 删除所有与该菜单关联的所有用户的权限信息
    void clearUserAuthorityInfoByMenuId(String menuId);

    LambdaQueryWrapper<SysUser> createQueryWrapper(SysUser param);
}
