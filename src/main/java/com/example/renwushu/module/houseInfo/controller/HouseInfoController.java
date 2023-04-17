package com.example.renwushu.module.houseInfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.houseInfo.entity.HouseInfo;
import com.example.renwushu.module.houseInfo.entity.dto.HouseInfoParam;
import com.example.renwushu.module.houseInfo.service.HouseInfoService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 查看房子基础表 前端控制器
 * </p>
 *
 * @author jjh
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/houseInfo")
@Api(tags = "HouseInfoController",  value = "基础表")
public class HouseInfoController {
    @Autowired
    private HouseInfoService houseInfoService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody HouseInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
        param.setCreateDate(new Date());
        param.setUpdateDate(new Date());
        param.setOriginalPrice(param.getPrice());
        boolean result = houseInfoService.save(param);
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
    public AjaxJson update(@RequestBody HouseInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setUpdateDate(new Date());
        if (param.getOriginalPrice() == null){
            param.setOriginalPrice(param.getPrice());
        }
        boolean result = houseInfoService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody HouseInfo param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setUpdateDate(new Date());
        param.setStatus("1");
        boolean result = houseInfoService.updateById(param);
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        HouseInfo result = houseInfoService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public AjaxJson listAll(HouseInfoParam param) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<HouseInfo> queryWrapper = createQueryWrapper(param);
        List<HouseInfo> result = houseInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @GetMapping("/page")
    public AjaxJson page(int pageNum, int pageSize, String name){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<HouseInfo> page = new Page<>(pageNum, pageSize);

        IPage<HouseInfo> page1 = houseInfoService.page(page, new LambdaQueryWrapper<HouseInfo>());
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
    static QueryWrapper<HouseInfo> createQueryWrapper(HouseInfoParam param){
        QueryWrapper<HouseInfo> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(param.getStatus())) {
            queryWrapper.eq(HouseInfoParam.STATUS, param.getStatus());
        } else {
            queryWrapper.eq(HouseInfoParam.STATUS, "0");
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && "asc".equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(param.getOrderBy());
            } else {
                queryWrapper.orderByDesc(param.getOrderBy());
            }
        } else {
            queryWrapper.orderByDesc(HouseInfo.CREATE_DATE);
        }
        return queryWrapper;
    }
}
