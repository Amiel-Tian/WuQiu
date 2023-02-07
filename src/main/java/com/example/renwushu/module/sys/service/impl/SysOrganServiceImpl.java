package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.dao.SysOrganMapper;
import com.example.renwushu.module.sys.entity.SysOrgan;
import com.example.renwushu.module.sys.service.SysOrganService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-05
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements SysOrganService {

    @Override
    public List<SysOrgan> getNavAll() {
        // 获取所有菜单
        LambdaQueryWrapper<SysOrgan> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysOrgan::getStatus, QueryField.STATU_NOR_);
        List<SysOrgan> sysMenus = list(queryWrapper);
        List<SysOrgan> collect = sysMenus.stream().sorted(Comparator.comparing(SysOrgan::getSort,Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());

        List<SysOrgan> menus = buildTreeMenu(collect);
        return menus;
    }

    /**
     * 把list转成树形结构的数据
     */
    private List<SysOrgan> buildTreeMenu(List<SysOrgan> collect) {
        List<SysOrgan> finalMenus = new ArrayList<>();
        for (SysOrgan menu : collect) {
            // 先寻找各自的孩子
            for (SysOrgan e : collect) {
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


    @Override
    public LambdaQueryWrapper<SysOrgan> createQueryWrapper(SysOrgan param) {
        LambdaQueryWrapper<SysOrgan> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getParentId())) {
            queryWrapper.eq(SysOrgan::getParentId, param.getParentId());
        }
        if (StringUtils.isNotBlank(param.getName())) {
            queryWrapper.like(SysOrgan::getName, param.getName());
        }

        /**/
        if (param.getStatus() != null) {
            queryWrapper.eq(SysOrgan::getStatus, param.getStatus());
        } else {
//            queryWrapper.eq(SysOrgan::getStatus, QueryField.STATU_NOR);
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(param.getOrderBy())) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysOrgan::getStatus);
            } else {
                queryWrapper.orderByDesc(SysOrgan::getOrderBy);
            }
        } else {
            queryWrapper.orderByAsc(SysOrgan::getSort);
        }
        return queryWrapper;
    }
}
