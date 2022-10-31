package com.example.renwushu.module.sys.service;

import com.example.renwushu.module.sys.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.renwushu.module.sys.entity.dto.SysMenuDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuDto> getcurrentUserNav();
}
