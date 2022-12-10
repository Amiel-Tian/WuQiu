package com.example.renwushu.module.renwushu.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwushu.business.entity.BusinessInfo;
import com.example.renwushu.module.renwushu.business.service.BusinessInfoService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-12-09
 */
@Controller
@ResponseBody
@RequestMapping("/businessInfo")
@Api(tags = "业务信息基础",  value = "businessInfo")
public class BusinessInfoController {
    @Autowired
    private BusinessInfoService businessInfoService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());
        param.setCreateDate(new Date());
        boolean result = businessInfoService.save(param);
        if (result) {
            Map map = new HashMap();
            map.put("id", param.getId());
            ajaxJson.setBody(map);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxJson update(@RequestBody BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setUpdateDate(new Date());
        boolean result = businessInfoService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = businessInfoService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = businessInfoService.removeById(param.getId());
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        BusinessInfo result = businessInfoService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());
        LambdaQueryWrapper<BusinessInfo> queryWrapper = businessInfoService.createQueryWrapper(param);
        List<BusinessInfo> result = businessInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(BusinessInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        IPage<BusinessInfo> page = new Page<>(param.getPageNum(), param.getPageSize());
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());

        IPage<BusinessInfo> page1 = businessInfoService.page(page, businessInfoService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }


    @GetMapping("/treeData")
    @ResponseBody
    public AjaxJson treeData() {
        Map map = new HashMap();
        return new AjaxJson().setData(businessInfoService.getTreeData());
    }

}
