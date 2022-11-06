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
    private SysRoleMenuService sysRoleMenuService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();
//        param.setCreateBy();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

        boolean result = sysRoleService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByRoleId(param.getId());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysRole param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatu(0);
        boolean result = sysRoleService.updateById(param);
        if (result){

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
        LambdaQueryWrapper<SysRole> queryWrapper = createQueryWrapper(param);
        List<SysRole> result = sysRoleService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/page")
    public AjaxJson page(SysRole sysUser){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysRole> page = new Page<>(sysUser.getPageNum(), sysUser.getPageSize());

        IPage<SysRole> page1 = sysRoleService.page(page, new LambdaQueryWrapper<SysRole>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static LambdaQueryWrapper<SysRole> createQueryWrapper(SysRole param){
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getStatu() != null) {
            queryWrapper.eq(SysRole::getStatu, param.getStatu());
        } else {
            queryWrapper.eq(SysRole::getStatu, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysRole::getStatu);
            } else {
                queryWrapper.orderByDesc(SysRole::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysRole::getCreatedTime);
        }
        return queryWrapper;
    }
}