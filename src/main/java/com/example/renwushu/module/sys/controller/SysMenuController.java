package com.example.renwushu.module.sys.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.entity.SysRoleMenu;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysMenuService;
import com.example.renwushu.module.sys.service.SysRoleMenuService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-10-31
 */
@Controller
@ResponseBody
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysMenuService sysMenuService;
    /**
     * 获取当前用户的菜单栏以及权限
     */
    @GetMapping("/nav")
    @ResponseBody
    public AjaxJson nav() {
        SysUser sysUser = sysUserService.getLoginUser();
        if (sysUser == null){
            return new AjaxJson().setData(MapUtil.builder().put("nav", new ArrayList<>()).put("authoritys", new ArrayList<>()).map());
        }
        String[] authoritys = StringUtils.tokenizeToStringArray(sysUserService.getAuthorityByUser(sysUser.getId()), ",");
        return new AjaxJson().setData(MapUtil.builder().put("nav", sysMenuService.getcurrentUserNav()).put("authoritys", authoritys).map());
    }
    @GetMapping("/navAll")
    @ResponseBody
    public AjaxJson navAll() {
        Map map = new HashMap();
        map.put("nav", sysMenuService.getNavAll());
        return new AjaxJson().setData(map);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysMenu param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setId(IdHelp.UUID());
        param.setStatus(QueryField.STATU_NOR_);
        param.setCreatedTime(new Date());

        boolean result = sysMenuService.save(param);
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
    public AjaxJson update(@RequestBody SysMenu param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysMenuService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
            /*
             * 删除权限
             * */
//            LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper();
//            queryWrapper.eq(SysRoleMenu :: getMenuId, param.getId());
//            sysRoleMenuService.remove(queryWrapper);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysMenu param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = sysMenuService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysMenu param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysMenuService.removeById(param.getId());

        if (result){
            sysUserService.clearUserAuthorityInfoByMenuId(param.getId());
            /*
             * 递归删除子项权限
             * */
            LambdaQueryWrapper<SysMenu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            menuLambdaQueryWrapper.eq(SysMenu::getParentId, param.getId());
            List<SysMenu> list = sysMenuService.list(menuLambdaQueryWrapper);
            if (list != null && list.size() > 0){
                list.forEach(item -> {
                    remove(item);
                });
            }
            /*
             * 删除权限
             * */
            LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(SysRoleMenu :: getMenuId, param.getId());
            sysRoleMenuService.remove(queryWrapper);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysMenu result = sysMenuService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysMenu param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysMenu> queryWrapper = sysMenuService.createQueryWrapper(param);
        List<SysMenu> result = sysMenuService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysMenu param){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysMenu> page = new Page<>(param.getPageNum(), param.getPageSize());

        IPage<SysMenu> page1 = sysMenuService.page(page, sysMenuService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
}
