package com.example.renwushu.module.renwushu.resume.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwushu.resume.entity.ResumeInfo;
import com.example.renwushu.module.renwushu.resume.service.ResumeInfoService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 简历基础信息表 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-08-27
 */
@Controller
@RequestMapping("/resumeInfo")
@Api(tags = "简历基础信息表", value = "resumeInfo")
@ResponseBody
public class ResumeInfoController {
    @Autowired
    private ResumeInfoService resumeInfoService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody ResumeInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser loginUser = sysUserService.getLoginUser();
        param.setCreateBy(loginUser.getId());
        param.setId(IdHelp.UUID());
        param.setCreateDate(new Date());
        boolean result = resumeInfoService.save(param);
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
    public AjaxJson update(@RequestBody ResumeInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setUpdateDate(new Date());
        boolean result = resumeInfoService.updateById(param);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ResumeInfo result = resumeInfoService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public AjaxJson listAll(ResumeInfo param) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<ResumeInfo> queryWrapper = createQueryWrapper(param);
        List<ResumeInfo> result = resumeInfoService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "验证", notes = "验证")
    @RequestMapping(value = "/jurisdiction", method = RequestMethod.POST)
    public AjaxJson jurisdiction(String param) {
        AjaxJson ajaxJson = new AjaxJson();
        boolean result = false;
        if (StringUtils.isNotEmpty(param) && param.equals("3294@wq.t")) {
            result = true;
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    static QueryWrapper<ResumeInfo> createQueryWrapper(ResumeInfo param) {
        QueryWrapper<ResumeInfo> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(param.getCreateBy())) {
            queryWrapper.eq("create_by", param.getCreateBy());
        }

        return queryWrapper;
    }
}
