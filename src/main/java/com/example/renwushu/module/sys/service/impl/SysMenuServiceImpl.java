package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.dao.SysUserMapper;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.dao.SysMenuMapper;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.dto.SysMenuDto;
import com.example.renwushu.module.sys.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysMenuDto> getcurrentUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.getByUser(new SysUser().setLoginname(username));
        // 获取用户的所有菜单
        List<String> menuIds = sysUserMapper.getNavMenuIds(sysUser.getId());
        List<SysMenu> menus = buildTreeMenu(this.listByIds(menuIds));
        return convert(menus);
    }
    @Override
    public List<SysMenuDto> getNavAll() {
        // 获取用户的所有菜单
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysMenu::getStatu, QueryField.STATU_NOR_);
        List<SysMenu> list = list(queryWrapper);
        List<SysMenu> menus = buildTreeMenu(list);
        return convert(menus);
    }

    /**
     * 把list转成树形结构的数据
     */
    private List<SysMenu> buildTreeMenu(List<SysMenu> menus) {
        List<SysMenu> finalMenus = new ArrayList<>();
        for (SysMenu menu : menus) {
            // 先寻找各自的孩子
            for (SysMenu e : menus) {
                if (e.getParentId().equals(menu.getId())) {
                    menu.getChildren().add(e);
                }
            }
            // 提取出父节点
            if (menu.getParentId().equals("0")) {
                finalMenus.add(menu);
            }
        }
        return finalMenus;
    }

    /**
     * menu转menuDto
     */
    private List<SysMenuDto> convert(List<SysMenu> menus) {
        List<SysMenuDto> menuDtos = new ArrayList<>();
        menus.forEach(m -> {
            SysMenuDto dto = new SysMenuDto();
            dto.setId(m.getId());
            dto.setPerms(m.getPerms());
            dto.setName(m.getName());
            dto.setComponent(m.getComponent());
            dto.setIcon(m.getIcon());
            dto.setPath(m.getPath());
            dto.setType(m.getType());
            dto.setSort(m.getSort());
            if (m.getChildren().size() > 0) {
                dto.setChildren(convert(m.getChildren()));
            }
            menuDtos.add(dto);
        });
        return menuDtos;
    }
}
