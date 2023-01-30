package com.example.renwushu.module.sys.controller;

import com.example.renwushu.config.webSocket.NetgateHandler;
import com.example.renwushu.module.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;

@RestController
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    private NetgateHandler webSocketHandler;

    @PostMapping("/sentMessage")
    public void sentMessage(String userId,String message){
        try {
            webSocketHandler.sendMessageToUser(userId,new TextMessage(message));
            ArrayList<SysUser> a = new ArrayList<SysUser>();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
