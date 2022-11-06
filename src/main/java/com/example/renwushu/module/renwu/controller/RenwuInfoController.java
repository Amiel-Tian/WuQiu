package com.example.renwushu.module.renwu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwu.entity.RenwuInfo;
import com.example.renwushu.module.renwu.service.RenwuInfoService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
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

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
//        param.setCreateBy();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean result = renwuInfoService.save(param);
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
    public AjaxJson update(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = renwuInfoService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatu(0);
        boolean result = renwuInfoService.updateById(param);
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
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public AjaxJson listAll(RenwuInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<RenwuInfo> queryWrapper = createQueryWrapper(param);
        List<RenwuInfo> result = renwuInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(RenwuInfo param){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<RenwuInfo> page = new Page<>(param.getPageNum(), param.getPageSize());

        IPage<RenwuInfo> page1 = renwuInfoService.page(page, new LambdaQueryWrapper<RenwuInfo>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static LambdaQueryWrapper<RenwuInfo> createQueryWrapper(RenwuInfo param){
        LambdaQueryWrapper<RenwuInfo> queryWrapper = new LambdaQueryWrapper<>();

        if (param.getStatu() != null) {
            queryWrapper.eq(RenwuInfo::getStatu, param.getStatu());
        } else {
            queryWrapper.eq(RenwuInfo::getStatu, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && "asc".equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(RenwuInfo::getOrderBy);
            } else {
                queryWrapper.orderByDesc(RenwuInfo::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(RenwuInfo::getCreateDate);
        }
        return queryWrapper;
    }

}
