package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysOrgan;
import com.example.renwushu.module.sys.entity.SysUserOrgan;
import com.example.renwushu.module.sys.service.SysOrganService;
import com.example.renwushu.module.sys.service.SysUserOrganService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
 * @since 2023-02-05
 */
@Controller
@ResponseBody
@RequestMapping("/sysOrgan")
public class SysOrganController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserOrganService sysUserOrganService;
    @Resource
    private SysOrganService sysOrganService;

    @GetMapping("/navAll")
    @ResponseBody
    public AjaxJson navAll() {
        Map map = new HashMap();
        map.put("nav", sysOrganService.getNavAll());
        return new AjaxJson().setData(map);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysOrgan param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setId(IdHelp.UUID());
        param.setStatus(QueryField.STATU_NOR_);
        param.setCreatedTime(new Date());

        boolean result = sysOrganService.save(param);
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
    public AjaxJson update(@RequestBody SysOrgan param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysOrganService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
            /*
             * 删除权限
             * */

        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysOrgan param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = sysOrganService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysOrgan param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysOrganService.removeById(param.getId());

        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
            /*
             * 递归删除子项权限
             * */
            LambdaQueryWrapper<SysOrgan> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menuLambdaQueryWrapper.eq(SysOrgan::getParentId, param.getId());
            List<SysOrgan> list = sysOrganService.list(menuLambdaQueryWrapper);
            if (list != null && list.size() > 0){
                list.forEach(item -> {
                    remove(item);
                });
            }
            /*
             * 删除权限
             * */
            LambdaQueryWrapper<SysUserOrgan> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(SysUserOrgan :: getOrganId, param.getId());
            sysUserOrganService.remove(queryWrapper);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysOrgan result = sysOrganService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysOrgan param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysOrgan> queryWrapper = sysOrganService.createQueryWrapper(param);
        List<SysOrgan> result = sysOrganService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysOrgan param){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysOrgan> page = new Page<>(param.getPageNum(), param.getPageSize());

        IPage<SysOrgan> page1 = sysOrganService.page(page, sysOrganService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
}
