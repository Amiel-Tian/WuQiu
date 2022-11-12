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
import java.util.Comparator;
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
        List<SysMenu> sysMenus = this.listByIds(menuIds);

        List<SysMenu> collect = sysMenus.stream().sorted(Comparator.comparing(SysMenu::getSort,Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());

        List<SysMenu> menus = buildTreeMenu(collect);
        return convert(menus);
    }
    @Override
    public List<SysMenuDto> getNavAll() {
        // 获取所有菜单
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysMenu::getStatus, QueryField.STATU_NOR_);
        List<SysMenu> sysMenus = list(queryWrapper);
        List<SysMenu> collect = sysMenus.stream().sorted(Comparator.comparing(SysMenu::getSort,Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());

        List<SysMenu> menus = buildTreeMenu(collect);
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
                if (e != null && menu != null && e.getParentId().equals(menu.getId())) {
                    menu.getChildren().add(e);
                }
            }
            // 提取出父节点
            if (menu != null && menu.getParentId().equals("0")) {
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

    @Override
    public LambdaQueryWrapper<SysMenu> createQueryWrapper(SysMenu param){
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getParentId() != null) {
            queryWrapper.eq(SysMenu::getParentId, param.getParentId());
        }
        /**/
        if (param.getStatus() != null) {
            queryWrapper.eq(SysMenu::getStatus, param.getStatus());
        } else {
//            queryWrapper.eq(SysMenu::getStatus, QueryField.STATU_NOR);
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(param.getOrderBy())) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysMenu::getStatus);
            } else {
                queryWrapper.orderByDesc(SysMenu::getOrderBy);
            }
        } else {
            queryWrapper.orderByAsc(SysMenu::getSort);
        }
        return queryWrapper;
    }
}
