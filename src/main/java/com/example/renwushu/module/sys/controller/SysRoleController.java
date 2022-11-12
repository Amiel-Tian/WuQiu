package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.*;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.service.SysRoleMenuService;
import com.example.renwushu.module.sys.service.SysRoleService;
import com.example.renwushu.module.sys.service.SysUserRoleService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.thymeleaf.util.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-10-31
 */
@Controller
@ResponseBody
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setId(IdHelp.UUID());
        /*
         * 同步添加权限菜单表
         * */
        if (!ListUtils.isEmpty(param.getMenuIdList() )){
            LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<SysRoleMenu>();
            queryWrapper.eq(SysRoleMenu::getRoleId, param.getId());
            sysRoleMenuService.remove(queryWrapper);

            for (String s : param.getMenuIdList()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(IdHelp.UUID());
                sysRoleMenu.setRoleId(param.getId());
                sysRoleMenu.setMenuId(s);
                sysRoleMenuService.save(sysRoleMenu);
            }
        }

        boolean result = sysRoleService.save(param);
        if (result){
            Map map = new HashMap();
            map.put("id",param.getId());
            ajaxJson.setBody(map);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxJson update(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysRoleService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByRoleId(param.getId());

            /*
             * 同步修改权限菜单表
             * */
            if (!ListUtils.isEmpty(param.getMenuIdList() )){
                LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<SysRoleMenu>();
                queryWrapper.eq(SysRoleMenu::getRoleId, param.getId());
                sysRoleMenuService.remove(queryWrapper);

                for (String s : param.getMenuIdList()) {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setId(IdHelp.UUID());
                    sysRoleMenu.setRoleId(param.getId());
                    sysRoleMenu.setMenuId(s);
                    sysRoleMenuService.save(sysRoleMenu);
                }
            }
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = sysRoleService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByRoleId(param.getId());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysRoleService.removeById(param.getId());

        if (result){
            sysUserService.clearUserAuthorityInfoByRoleId(param.getId());
            /*
             * 同步清空权限菜单表
             * */
            LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(SysUserRole :: getRoleId, param.getId());
            sysUserRoleService.remove(queryWrapper);
            /*
             * 同步清空权限用户表
             * */
            LambdaQueryWrapper<SysRoleMenu> queryWrapper1 = new LambdaQueryWrapper();
            queryWrapper1.eq(SysRoleMenu :: getRoleId, param.getId());
            sysRoleMenuService.remove(queryWrapper1);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysRole result = sysRoleService.getById(id);

        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId,id);
        List<SysRoleMenu> list = sysRoleMenuService.list(queryWrapper);

        if (!ListUtils.isEmpty(list)) {
            result.setMenuIdList(list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
        }

        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysRole> queryWrapper = sysRoleService.createQueryWrapper(param);
        List<SysRole> result = sysRoleService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysRole sysUser){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysRole> page = new Page<>(sysUser.getPageNum(), sysUser.getPageSize());

        IPage<SysRole> page1 = sysRoleService.page(page, new LambdaQueryWrapper<SysRole>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
}
