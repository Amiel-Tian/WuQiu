package com.example.renwushu.module.renwushu.resume.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.module.renwushu.resume.entity.ResumeContent;
import com.example.renwushu.module.renwushu.resume.service.ResumeContentService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 简历内容 前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2022-09-08
 */
@Controller
@ResponseBody
@RequestMapping("/resumeContent")
@Api(tags = "简历内容", value = "resumeContent")
public class ResumeContentController {
    @Autowired
    private ResumeContentService resumeContentService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody ResumeContent param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setId(IdHelp.UUID());
        param.setCreateDate(new Date());
        boolean result = resumeContentService.save(param);
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
    public AjaxJson update(@RequestBody ResumeContent param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setUpdateDate(new Date());
        boolean result = resumeContentService.updateById(param);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "获取", notes = "获取")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AjaxJson getById(String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ResumeContent result = resumeContentService.getById(id);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public AjaxJson listAll(ResumeContent param) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper<ResumeContent> queryWrapper = createQueryWrapper(param);
        List<ResumeContent> result = resumeContentService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public AjaxJson delete(@RequestBody ResumeContent param) {
        AjaxJson ajaxJson = new AjaxJson();
        param.setUpdateDate(new Date());
        param.setStatus(QueryField.STATU_DEL);
        boolean result = resumeContentService.updateById(param);
        if (result) {
            Map map = new HashMap();
            map.put("id", param.getId());
            ajaxJson.setBody(map);
        }
        ajaxJson.setData(result);
        return ajaxJson;
    }

    static QueryWrapper<ResumeContent> createQueryWrapper(ResumeContent param) {
        QueryWrapper<ResumeContent> queryWrapper = new QueryWrapper<>();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(param.getResumeId())) {
            queryWrapper.eq(ResumeContent.RESUME_ID, param.getResumeId());
        }

        queryWrapper.orderByAsc(ResumeContent.SORT_);
        queryWrapper.eq(ResumeContent.STATUS, QueryField.STATU_NOR);

        return queryWrapper;
    }
}
