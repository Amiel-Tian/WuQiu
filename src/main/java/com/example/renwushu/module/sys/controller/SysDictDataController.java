package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysDictData;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysDictDataService;
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
 * 字典类型表 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Controller
@ResponseBody
@RequestMapping("/sysDictData")
public class SysDictDataController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDictDataService sysDictDataService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysDictData param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setId(IdHelp.UUID());

        boolean result = sysDictDataService.save(param);
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
    public AjaxJson update(@RequestBody SysDictData param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysDictDataService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysDictData param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(QueryField.STATU_NOR);
        boolean result = sysDictDataService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysDictDataService.removeById(param.getId());

        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysDictData result = sysDictDataService.getById(id);

        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysDictData param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysDictData> queryWrapper = createQueryWrapper(param);
        List<SysDictData> result = sysDictDataService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysDictData sysUser){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysDictData> page = new Page<>(sysUser.getPageNum(), sysUser.getPageSize());

        IPage<SysDictData> page1 = sysDictDataService.page(page, new LambdaQueryWrapper<SysDictData>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static LambdaQueryWrapper<SysDictData> createQueryWrapper(SysDictData param){
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getStatus() != null) {
            queryWrapper.eq(SysDictData::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(SysDictData::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysDictData::getStatus);
            } else {
                queryWrapper.orderByDesc(SysDictData::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysDictData::getCreateDate);
        }
        return queryWrapper;
    }
}
