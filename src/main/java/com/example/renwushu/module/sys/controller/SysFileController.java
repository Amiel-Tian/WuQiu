package com.example.renwushu.module.sys.controller;

import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.config.minio.UploadResponse;
import com.example.renwushu.module.sys.entity.SysFile;
import com.example.renwushu.module.sys.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sysFile")
@Api(tags = "SysFileCOntroller",  value = "文件表")
public class SysFileController {
    @Resource
    private SysFileService sysFileService;

    private final Log log = LogFactory.getLog(SysFileController.class);

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @RequestMapping(value = "/uploadAttachment", method = RequestMethod.POST)
    public AjaxJson uploadAttachment(MultipartFile file){
        AjaxJson ajaxJson = new AjaxJson();

        try {
            UploadResponse uploadResponse = sysFileService.uploadAttachment(file);
            ajaxJson.setData(uploadResponse);
        } catch (Exception e) {
            log.info("上传文件失败！", e);
            return AjaxJson.returnExceptionInfo("上传失败");
        }

        return ajaxJson;
    }
    @ApiOperation(value = "上传临时文件", notes = "上传临时文件")
    @RequestMapping(value = "/uploadTemporary", method = RequestMethod.POST)
    public AjaxJson uploadTemporary( MultipartFile file){
        AjaxJson ajaxJson = new AjaxJson();

        try {
            UploadResponse uploadResponse = sysFileService.uploadTemporary(file);
            ajaxJson.setData(uploadResponse);
        } catch (Exception e) {
            log.info("上传文件失败！", e);
            return AjaxJson.returnExceptionInfo("上传失败");
        }

        return ajaxJson;
    }
    @ApiOperation(value = "获取附件", notes = "获取附件")
    @RequestMapping(value = "/getByIds", method = RequestMethod.GET)
    public AjaxJson getByIds(String ids){
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtils.isEmpty(ids)){
            return AjaxJson.returnExceptionInfo("ids必传");
        }
        try {
            List<SysFile> fileList = sysFileService.getByIds(ids);
            ajaxJson.setData(fileList);
        } catch (Exception e) {
            log.info("获取失败", e);
            return AjaxJson.returnExceptionInfo("获取失败");
        }

        return ajaxJson;
    }
}
