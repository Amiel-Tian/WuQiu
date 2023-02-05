package com.example.renwushu.module.renwushu.renwu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwushu.renwu.entity.RenwuProject;
import com.example.renwushu.module.renwushu.renwu.service.RenwuProjectService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-11-13
 */
@Controller
@ResponseBody
@RequestMapping("/renwuProject")
@Api(tags = "任务书项目",  value = "renwuProject")
public class RenwuProjectController {
    @Resource
    private RenwuProjectService renwuProjectService;
    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
        boolean result = renwuProjectService.save(param);
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
    public AjaxJson update(@RequestBody RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = renwuProjectService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = renwuProjectService.updateById(param);
        if (result) {

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = renwuProjectService.removeById(param.getId());
        if (result){

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        RenwuProject result = renwuProjectService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser loginUser = sysUserService.getLoginUser();
        LambdaQueryWrapper<RenwuProject> queryWrapper = renwuProjectService.createQueryWrapper(param);
        List<RenwuProject> result = renwuProjectService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(RenwuProject param) {
        AjaxJson ajaxJson = new AjaxJson();
        IPage<RenwuProject> page = new Page<>(param.getPageNum(), param.getPageSize());
        SysUser loginUser = sysUserService.getLoginUser();

        IPage<RenwuProject> page1 = renwuProjectService.page(page, renwuProjectService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }

}
