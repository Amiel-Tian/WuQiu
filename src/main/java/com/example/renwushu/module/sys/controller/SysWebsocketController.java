package com.example.renwushu.module.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.config.webSocket.NetgateHandler;
import com.example.renwushu.module.sys.entity.SysWebsocket;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.module.sys.service.SysWebsocketService;
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
 * @since 2023-02-06
 */
@Controller
@ResponseBody
@RequestMapping("/sysWebSocket")
public class SysWebsocketController {
    @Resource
    private SysWebsocketService sysWebsocketService;
    @Resource
    private NetgateHandler webSocketHandler;
    @Resource
    private SysUserService sysUserService;

    @PostMapping("/sentMessage")
    public void sentMessage(@RequestBody SysWebsocket param){
        try {
            if (param == null || StringUtils.isBlank(param.getSendId())|| StringUtils.isBlank(param.getReceiveId())){
                return;
            }
            param.setId(IdHelp.UUID());
            sysWebsocketService.save(param);
            webSocketHandler.sendMessageToUser(param.getReceiveId(),new TextMessage(JSON.toJSONString(param)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public AjaxJson listAll(SysWebsocket param) {
        AjaxJson ajaxJson = new AjaxJson();
        LambdaQueryWrapper<SysWebsocket> queryWrapper = sysWebsocketService.createQueryWrapper(param);
        List<SysWebsocket> result = sysWebsocketService.list(queryWrapper);
        ajaxJson.setData(result);
        return ajaxJson;
    }

}
