package com.example.renwushu.module.chat.messages.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.config.webSocket.NetgateHandler;
import com.example.renwushu.module.chat.messages.dao.MessagesMapper;
import com.example.renwushu.module.chat.messages.entity.Messages;
import com.example.renwushu.module.chat.messages.service.MessagesService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.dto.SysWebSocketDto;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.IdHelp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RedStar
 * @since 2023-02-09
 */
@Controller
@ResponseBody
@RequestMapping("/messages")
public class MessagesController {
    @Resource
    private MessagesService messagesService;
    @Resource
    private MessagesMapper messagesMapper;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private NetgateHandler webSocketHandler;
    
    @PostMapping("/sentMessage")
    public void sentMessage(@RequestBody Messages param){
        try {
            if (param == null || StringUtils.isBlank(param.getSendId())|| StringUtils.isBlank(param.getReceiveId())){
                return;
            }
            param.setId(IdHelp.UUID());
            messagesService.save(param);
            AjaxJson ajaxJson = new AjaxJson();
            ajaxJson.setStatusCode(StatusCode.SUCESS);
            ajaxJson.setData(new SysWebSocketDto().setType("message").setData(param));
            webSocketHandler.sendMessageToUser(param.getReceiveId(),new TextMessage(JSON.toJSONString(ajaxJson)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/getUserRecord", method = RequestMethod.GET)
    public AjaxJson getUserRecord() {
        AjaxJson ajaxJson = new AjaxJson();

        SysUser loginUser = sysUserService.getLoginUser();

        List<SysUser> sysUserList = messagesMapper.userRecord(loginUser.getId());


        ajaxJson.setData(sysUserList);
        return ajaxJson;
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson datas(Messages param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<Messages> queryWrapper = messagesService.createQueryWrapper(param);
        List<Messages> result = messagesService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }
}
