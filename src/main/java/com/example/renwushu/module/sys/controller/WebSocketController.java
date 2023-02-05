package com.example.renwushu.module.sys.controller;

import com.alibaba.fastjson.JSON;
import com.example.renwushu.config.webSocket.NetgateHandler;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.dto.WebSocketDto;
import com.example.renwushu.module.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@ResponseBody
@RequestMapping("/webSocket")
public class WebSocketController {
    @Resource
    private NetgateHandler webSocketHandler;
    @Resource
    private SysUserService sysUserService;

    @PostMapping("/sentMessage")
    public void sentMessage(@RequestBody WebSocketDto param){
        try {
            SysUser loginUser = sysUserService.getLoginUser();
            param.setSendId(loginUser.getId());
            param.setSendDate(new Date());
            webSocketHandler.sendMessageToUser(param.getReceiveId(),new TextMessage(JSON.toJSONString(param)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
