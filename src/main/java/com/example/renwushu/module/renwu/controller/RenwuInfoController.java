package com.example.renwushu.module.renwu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwu.entity.RenwuInfo;
import com.example.renwushu.module.renwu.service.RenwuInfoService;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import com.example.renwushu.utils.ToolUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-11-03
 */
@Controller
@ResponseBody
@RequestMapping("/renwuInfo")
public class RenwuInfoController {
    @Autowired
    private RenwuInfoService renwuInfoService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());
        param.setCreateDate(new Date());
        boolean result = renwuInfoService.save(param);
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
    public AjaxJson update(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = renwuInfoService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = renwuInfoService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = renwuInfoService.removeById(param.getId());
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        RenwuInfo result = renwuInfoService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());
        LambdaQueryWrapper<RenwuInfo> queryWrapper = renwuInfoService.createQueryWrapper(param);
        List<RenwuInfo> result = renwuInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        IPage<RenwuInfo> page = new Page<>(param.getPageNum(), param.getPageSize());
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());

        IPage<RenwuInfo> page1 = renwuInfoService.page(page, renwuInfoService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }



}
