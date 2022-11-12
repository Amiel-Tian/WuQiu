package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.module.sys.entity.SysDictData;
import com.example.renwushu.module.sys.entity.SysDictType;
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
        if (StringUtils.isNotBlank(param.getDictKey())){
            LambdaQueryWrapper<SysDictData> queryWrapper =  new LambdaQueryWrapper();
            queryWrapper.eq(SysDictData::getDictId, param.getDictId());
            queryWrapper.eq(SysDictData::getDictKey, param.getDictKey());
            SysDictData one = sysDictDataService.getOne(queryWrapper);
            if (one != null){
                return AjaxJson.returnExceptionInfo(StatusCode.CODE_VALUE_FAIL);
            }
        }
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
        if (StringUtils.isNotBlank(param.getDictKey())){
            LambdaQueryWrapper<SysDictData> queryWrapper =  new LambdaQueryWrapper();
            queryWrapper.eq(SysDictData::getDictId, param.getDictId());
            queryWrapper.eq(SysDictData::getDictKey, param.getDictKey());
            queryWrapper.ne(SysDictData::getId, param.getId());
            SysDictData one = sysDictDataService.getOne(queryWrapper);
            if (one != null){
                return AjaxJson.returnExceptionInfo(StatusCode.CODE_VALUE_FAIL);
            }
        }
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
        LambdaQueryWrapper<SysDictData> queryWrapper = sysDictDataService.createQueryWrapper(param);
        List<SysDictData> result = sysDictDataService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysDictData param){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysDictData> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<SysDictData> page1 = sysDictDataService.page(page, sysDictDataService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
}
