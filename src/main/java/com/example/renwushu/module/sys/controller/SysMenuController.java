package com.example.renwushu.module.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysMenuService;
import com.example.renwushu.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-10-31
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 获取当前用户的菜单栏以及权限
     */
    @GetMapping("/nav")
    @ResponseBody
    public AjaxJson nav(Principal principal) {
        String username = principal.getName();
        SysUser sysUser = sysUserService.getByUser(new SysUser().setUsername(username));
        // ROLE_Admin,sys:user:save
        String[] authoritys = StringUtils.tokenizeToStringArray(sysUserService.getAuthorityByUser(sysUser.getId()), ",");
        return new AjaxJson().setData(MapUtil.builder().put("nav", sysMenuService.getcurrentUserNav()).put("authoritys", authoritys).map());
    }
}
