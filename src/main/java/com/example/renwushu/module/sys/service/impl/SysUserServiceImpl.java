package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.dao.SysUserMapper;
import com.example.renwushu.module.sys.service.SysMenuService;
import com.example.renwushu.module.sys.service.SysRoleService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getLoginUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setLoginname(username);
        SysUser byUser = getByUser(sysUser);

        return byUser;
    }
    @Override
    public SysUser getByUser(SysUser sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();

        if (StringUtils.isNoneBlank(sysUser.getUsername())) {
            queryWrapper.eq("username", sysUser.getUsername());
        }
        if (StringUtils.isNoneBlank(sysUser.getLoginname())) {
            queryWrapper.eq("loginname", sysUser.getLoginname());
        }

        if (StringUtils.isNoneBlank(sysUser.getPassword())) {
            queryWrapper.eq("password", sysUser.getPassword());
        }
        queryWrapper.eq("status", QueryField.STATU_NOR);
        SysUser one = getOne(queryWrapper);

        return one;
    }

    @Override
    public String getAuthorityByUser(String userId) {
        SysUser sysUser = getById(userId);
        String authority = null;
        if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getLoginname())) {
//         优先从缓存获
            authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getLoginname());
        } else {
            List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id", "select role_id from sys_user_role where status = '"+ QueryField.STATU_NOR +"' and user_id = '" + userId + "'"));
            List<String> menuIds = sysUserMapper.getNavMenuIds(userId);
            List<SysMenu> menus = sysMenuService.listByIds(menuIds);
            String roleNames = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
            String permNames = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
            authority = roleNames.concat(",").concat(permNames);
            log.info("用户ID - {} ---拥有的权限：{}", userId, authority);
            redisUtil.set("GrantedAuthority:" + sysUser.getLoginname(), authority, 60 * 60);
        }
        return authority;
    }

    // 删除某个用户的权限信息
    @Override
    public void clearUserAuthorityInfo(String loginname) {
        redisUtil.deleteObject("GrantedAuthority:" + loginname);
    }

    // 删除所有与该角色关联的用户的权限信息
    @Override
    public void clearUserAuthorityInfoByRoleId(String roleId) {
        List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>().inSql("id", "select user_id from sys_user_role where role_id = '" + roleId+ "'"));
        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getLoginname());
        });
    }

    // 删除所有与该菜单关联的所有用户的权限信息
    @Override
    public void clearUserAuthorityInfoByMenuId(String menuId) {
        List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);
        sysUsers.forEach(u -> {
            if (u != null) {
                this.clearUserAuthorityInfo(u.getLoginname());
            }
        });
    }
    @Override
    public LambdaQueryWrapper<SysUser> createQueryWrapper(SysUser param){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getUsername())) {
            queryWrapper.like(SysUser::getUsername, param.getUsername());
        }
        if (StringUtils.isNotBlank(param.getLoginname())) {
            queryWrapper.like(SysUser::getLoginname, param.getLoginname());
        }

        if (param.getStatus() != null) {
            queryWrapper.eq(SysUser::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(SysUser::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotBlank(param.getOrderBy())) {
            if (StringUtils.isNotBlank(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysUser::getStatus);
            } else {
                queryWrapper.orderByDesc(SysUser::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysUser::getCreatedTime);
        }
        return queryWrapper;
    }
}
