package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();
//        param.setCreateBy();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean result = sysUserService.save(param);
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
    public AjaxJson update(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysUserService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatu(0);
        boolean result = sysUserService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser result = sysUserService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public AjaxJson listAll(SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysUser> queryWrapper = createQueryWrapper(param);
        List<SysUser> result = sysUserService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/page")
    public AjaxJson page(SysUser sysUser){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysUser> page = new Page<>(sysUser.getPageNum(), sysUser.getPageSize());

        IPage<SysUser> page1 = sysUserService.page(page, new LambdaQueryWrapper<SysUser>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static LambdaQueryWrapper<SysUser> createQueryWrapper(SysUser param){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getStatu() != null) {
            queryWrapper.eq(SysUser::getStatu, param.getStatu());
        } else {
            queryWrapper.eq(SysUser::getStatu, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysUser::getStatu);
            } else {
                queryWrapper.orderByDesc(SysUser::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysUser::getCreatedTime);
        }
        return queryWrapper;
    }
}
