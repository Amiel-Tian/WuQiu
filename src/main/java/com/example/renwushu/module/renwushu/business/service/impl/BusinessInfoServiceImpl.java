package com.example.renwushu.module.renwushu.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.renwushu.business.entity.BusinessInfo;
import com.example.renwushu.module.renwushu.business.dao.BusinessInfoMapper;
import com.example.renwushu.module.renwushu.business.entity.dto.BusinessInfoDto;
import com.example.renwushu.module.renwushu.business.service.BusinessInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.module.sys.entity.SysUser;
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
 * @since 2022-12-09
 */
@Service
public class BusinessInfoServiceImpl extends ServiceImpl<BusinessInfoMapper, BusinessInfo> implements BusinessInfoService {

    @Override
    public LambdaQueryWrapper<BusinessInfo> createQueryWrapper(BusinessInfo param){
        LambdaQueryWrapper<BusinessInfo> queryWrapper = new LambdaQueryWrapper<>();

        return queryWrapper;
    }

    @Override
    public List<BusinessInfoDto> getTreeData(SysUser loginUser) {
        // 获取所有菜单
        LambdaQueryWrapper<BusinessInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(BusinessInfo::getStatus, QueryField.STATU_NOR_);
        queryWrapper.and(wrapper -> wrapper.eq(BusinessInfo::getBusinessStatus , "public")
                .or().eq(BusinessInfo::getCreateBy, loginUser.getId()));
        List<BusinessInfo> sysMenus = list(queryWrapper);
        List<BusinessInfo> collect = sysMenus.stream().sorted(Comparator.comparing(BusinessInfo::getSort,Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());

        List<BusinessInfo> menus = buildTreeMenu(collect);
        return convert(menus);
    }

    private List<BusinessInfoDto> convert(List<BusinessInfo> menus) {
        List<BusinessInfoDto> menuDtos = new ArrayList<>();
        menus.forEach(m -> {
            BusinessInfoDto dto = new BusinessInfoDto();
            dto.setId(m.getId());
            dto.setName(m.getName());
            dto.setSort(m.getSort());
            if (m.getChildren().size() > 0) {
                dto.setChildren(convert(m.getChildren()));
            }
            menuDtos.add(dto);
        });
        return menuDtos;
    }

    private List<BusinessInfo> buildTreeMenu(List<BusinessInfo> collect) {
        List<BusinessInfo> finalMenus = new ArrayList<>();
        for (BusinessInfo menu : collect) {
            // 先寻找各自的孩子
            for (BusinessInfo e : collect) {
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

    ;
}
