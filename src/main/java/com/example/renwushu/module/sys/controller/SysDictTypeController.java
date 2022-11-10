package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysDictType;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysDictDataService;
import com.example.renwushu.module.sys.service.SysDictTypeService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Controller
@ResponseBody
@RequestMapping("/sysDictType")
public class SysDictTypeController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDictTypeService sysDictTypeService;
    @Autowired
    private SysDictDataService sysDictDataService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysDictType param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setId(IdHelp.UUID());

        boolean result = sysDictTypeService.save(param);
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
    public AjaxJson update(@RequestBody SysDictType param) {
        AjaxJson ajaxJson = new AjaxJson();
       
        boolean result = sysDictTypeService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysDictType param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(QueryField.STATU_NOR);
        boolean result = sysDictTypeService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysDictTypeService.removeById(param.getId());

        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysDictType result = sysDictTypeService.getById(id);

        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysDictType param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysDictType> queryWrapper = createQueryWrapper(param);
        List<SysDictType> result = sysDictTypeService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysDictType sysUser){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysDictType> page = new Page<>(sysUser.getPageNum(), sysUser.getPageSize());

        IPage<SysDictType> page1 = sysDictTypeService.page(page, new LambdaQueryWrapper<SysDictType>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static LambdaQueryWrapper<SysDictType> createQueryWrapper(SysDictType param){
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getStatus() != null) {
            queryWrapper.eq(SysDictType::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(SysDictType::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysDictType::getStatus);
            } else {
                queryWrapper.orderByDesc(SysDictType::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysDictType::getCreateDate);
        }
        return queryWrapper;
    }
}
