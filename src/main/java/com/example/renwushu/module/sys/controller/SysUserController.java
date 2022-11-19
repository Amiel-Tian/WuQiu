package com.example.renwushu.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.SysUserRole;
import com.example.renwushu.module.sys.service.SysRoleService;
import com.example.renwushu.module.sys.service.SysUserRoleService;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-10-31
 */
@Controller
@ResponseBody
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();
//        param.setCreateBy();
//        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        param.setId(IdHelp.UUID());
        param.setStatus(QueryField.STATU_NOR_);
        if (StringUtils.isNotBlank(param.getPassword())) {
            param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
        }
        if (!ListUtils.isEmpty(param.getRoleIdList() )){
            LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<SysUserRole>();
            queryWrapper.eq(SysUserRole::getUserId, param.getId());
            sysUserRoleService.remove(queryWrapper);

            for (String s : param.getRoleIdList()) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(IdHelp.UUID());
                sysUserRole.setUserId(param.getId());
                sysUserRole.setRoleId(s);
                sysUserRoleService.save(sysUserRole);
            }
        }

        boolean result = sysUserService.save(param);
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
    public AjaxJson update(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (StringUtils.isNotBlank(param.getPassword())) {
            param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
        }
        if (!ListUtils.isEmpty(param.getRoleIdList() )){
            LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<SysUserRole>();
            queryWrapper.eq(SysUserRole::getUserId, param.getId());
            sysUserRoleService.remove(queryWrapper);

            for (String s : param.getRoleIdList()) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(IdHelp.UUID());
                sysUserRole.setUserId(param.getId());
                sysUserRole.setRoleId(s);
                sysUserRoleService.save(sysUserRole);
            }
        }
        boolean result = sysUserService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfo(param.getLoginname());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "修改基础信息", notes = "修改基础信息")
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public AjaxJson updateInfo(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (StringUtils.isNotBlank(param.getPasswordVer())){
            SysUser loginUser = sysUserService.getLoginUser();
            if (!bCryptPasswordEncoder.matches(param.getPasswordVer(),loginUser.getPassword())){
                return AjaxJson.returnExceptionInfo(StatusCode.USER_LOGIN_PASSWORD_FAIL);
            }
            if (StringUtils.isNotBlank(param.getPassword())) {
                param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
            }
            boolean result = sysUserService.updateById(param);
            if (result){
                sysUserService.clearUserAuthorityInfo(param.getLoginname());
            }
            return ajaxJson;
        }
        return AjaxJson.returnExceptionInfo(StatusCode.USER_LOGIN_PASSWORD_FAIL);
    }
    @ApiOperation(value = "逻辑删除", notes = "逻辑删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public AjaxJson delete(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        param.setStatus(0);
        boolean result = sysUserService.updateById(param);
        if (result){
            sysUserService.clearUserAuthorityInfo(param.getLoginname());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public AjaxJson remove(@RequestBody SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();

        boolean result = sysUserService.removeById(param.getId());
        /*
         * 同步清空权限用户表
         * */
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUserRole :: getUserId, param.getId());
        sysUserRoleService.remove(queryWrapper);

        if (result){
            sysUserService.clearUserAuthorityInfo(param.getLoginname());
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser result = sysUserService.getById(id);

        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<SysUserRole>();
        queryWrapper.eq(SysUserRole::getUserId, id);
        List<SysUserRole> list = sysUserRoleService.list(queryWrapper);
        if (!ListUtils.isEmpty(list)){
            result.setRoleIdList(list.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
        }

        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysUser param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysUser> queryWrapper = sysUserService.createQueryWrapper(param);
        List<SysUser> result = sysUserService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    public AjaxJson page(SysUser param){
        AjaxJson ajaxJson = new AjaxJson();
        IPage<SysUser> page = new Page<>(param.getPageNum(), param.getPageSize());

        IPage<SysUser> page1 = sysUserService.page(page, sysUserService.createQueryWrapper(param));
        // 主要演示这里可以加条件。在name不为空的时候执行
        ajaxJson.setData(page1);
        return ajaxJson;
    }
}
