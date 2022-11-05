package com.example.renwushu.module.renwu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwu.entity.RenwuInfo;
import com.example.renwushu.module.renwu.entity.dto.RenwuInfoParam;
import com.example.renwushu.module.renwu.service.RenwuInfoService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public AjaxJson listAll(RenwuInfoParam param) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<RenwuInfo> queryWrapper = createQueryWrapper(param);
        List<RenwuInfo> result = renwuInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @GetMapping("/page")
    public AjaxJson page(int pageNum, int pageSize, String name){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<RenwuInfo> page = new Page<>(pageNum, pageSize);

        IPage<RenwuInfo> page1 = renwuInfoService.page(page, new LambdaQueryWrapper<RenwuInfo>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static QueryWrapper<RenwuInfo> createQueryWrapper(RenwuInfoParam param){
        QueryWrapper<RenwuInfo> queryWrapper = new QueryWrapper<>();

        if (param.getStatu() != null) {
            queryWrapper.eq("statu", param.getStatu());
        } else {
            queryWrapper.eq("statu", "1");
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && "asc".equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(param.getOrderBy());
            } else {
                queryWrapper.orderByDesc(param.getOrderBy());
            }
        } else {
            queryWrapper.orderByDesc("create_date");
        }
        return queryWrapper;
    }

}
