package com.example.renwushu.module.sys.controller;

import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.utils.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    private WebSocket webSocket;
    @PostMapping("/sentMessage")
    public void sentMessage(String userId,String message){
        try {
            webSocket.sendOneMessage(userId,message);
            ArrayList<SysUser> a = new ArrayList<SysUser>();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
