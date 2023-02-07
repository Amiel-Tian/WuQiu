package com.example.renwushu.config.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Websocket处理器
 */
@Slf4j
@Component
public class NetgateHandler extends TextWebSocketHandler {

//    @Autowired
//    private MqttGateway mqttGateway;


    /*
     * 网关连接集合
     * 第一级：设备序列号 sn
     * 第二级：用户唯一标识 openid
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocketSession>> netgates = new ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocketSession>>();


    /**
     * 处理前端发送的文本信息
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (!session.isOpen()) {
            log.info("连接已关闭，不再处理该连接的消息！");
            return;
        }
        String mes = ObjectUtils.toString(message.getPayload(), "");
        String pid = session.getAttributes().get("WEBSOCKET_PID").toString();
        String sn = session.getAttributes().get("WEBSOCKET_SN").toString();
        if (message == null || "".equals(mes)) {
            log.info("接收到空消息，不予处理。");
        } else if (mes.length() == 1) {
            //心跳消息过滤掉
            return;
        } else {
            session.sendMessage(message);
            //转发成mqtt消息
            String topic = "pay/" + pid + "/server/" + sn;
            log.info(topic);
            log.info("消息处理完成：" + mes);
//            mqttGateway.sendToMqtt(topic,mes);
        }
    }


    /**
     * 当新连接建立的时候，被调用
     * 连接成功时候，会触发页面上onOpen方法
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("正在初始化连接：" + session.getId());
        try {
            //初始化连接，把session存储起来
            this.initUsers(session);
        } catch (Exception e) {
            log.info("初始化连接异常-开始：" + e.getMessage());
            e.printStackTrace();
            log.info("初始化连接异常-结束：" + e.getMessage());
        }
        log.info("初始化连接完成：" + session.getId());
    }

    /**
     * 当连接关闭时被调用
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("正在关闭连接：" + session.getId() + ",isOpen:" + session.isOpen() + ";code：" + status.getCode());
        try {
            log.info("断开连接状态值" + status.getCode());
            this.removeSession(session);
        } catch (Exception e) {
            log.info("关闭连接异常-开始：" + e.getMessage());
            e.printStackTrace();
            log.info("关闭连接异常-结束：" + e.getMessage());
        }
        log.info("正在关闭完成：" + session.getId() + ",isOpen:" + session.isOpen() + ";code：" + status.getCode());
    }

    /**
     * 传输错误时调用
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("发生传输错误：" + session.getId() + ";session.isOpen():" + session.isOpen() + ";exception：" + exception.getMessage());
        exception.printStackTrace();
        if (session.isOpen()) {
            //try { session.close(); } catch (Exception e) {e.printStackTrace();}
        } else {
            try {
                this.removeSession(session);
            } catch (Exception e) {
                log.info("传输错误处理异常-开始：" + e.getMessage());
                e.printStackTrace();
                log.info("传输错误处理异常-结束：" + e.getMessage());
            }
        }
        log.info("错误处理结束：" + session.getId() + ";session.isOpen():" + session.isOpen() + ";exception：" + exception.getMessage());
    }


    public synchronized void sendMsgToNetgateSn(String sn, String msg) {
        if (netgates.size() > 0 && netgates.containsKey(sn) && !netgates.get(sn).isEmpty()) {
            //获取EID对应的后台管理连接 多个
            for (WebSocketSession ws : netgates.get(sn).values()) {
                log.info("对网关指令开始发送啦:sn=" + sn + "消息内容" + msg);
                try {
                    ws.sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    log.info("发生了异常：" + e.getMessage());
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }
    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        for (ConcurrentHashMap<String, WebSocketSession> netgate : netgates.values()){
            WebSocketSession ws = netgate.get(userId);
            if(ws == null){
                return;
            }
            try {
                this.handleTextMessage(ws, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某些用户发送消息
     * @param userIds
     * @param message
     */
    public void sendMessageToUsers(ArrayList<String> userIds, TextMessage message) {
        for (ConcurrentHashMap<String, WebSocketSession> netgate : netgates.values()){
            for (WebSocketSession ws : netgate.values()) {
                if (ws != null && userIds.indexOf(ws.getId()) > -1){
                    try {
                        this.handleTextMessage(ws, message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //连接接入的处理方法
    private synchronized void initUsers(WebSocketSession session) {
        String pid = (String) session.getAttributes().get("WEBSOCKET_PID");
        String sn = (String) session.getAttributes().get("WEBSOCKET_SN");
        String openid = (String) session.getAttributes().get("WEBSOCKET_OPENID");
        if (StringUtils.isNotEmpty(pid) && StringUtils.isNotEmpty(sn) && StringUtils.isNotEmpty(openid)) {
            ConcurrentHashMap<String, WebSocketSession> netgate = netgates.get(sn);
            if (netgate == null) {
                netgate = new ConcurrentHashMap<String, WebSocketSession>();
            }
            WebSocketSession session_exist = netgate.get(sn);
            if (session_exist != null) {
                log.info("检测到相同SN重复连接,SN:" + sn + ",连接ID:" + session_exist.getId() + ",准备清理失效的连接。。。");
                try {
                    session_exist.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            netgate.putIfAbsent(openid, session);
            netgates.put(sn, netgate);
        }
    }

    //连接被关闭时处理集合
    private synchronized void removeSession(WebSocketSession session) {
        String sn = (String) session.getAttributes().get("WEBSOCKET_SN");
        String openid = (String) session.getAttributes().get("WEBSOCKET_OPENID");
        if (netgates.get(sn).containsKey(openid)) {
            WebSocketSession exist_session = netgates.get(sn).get(openid);
            //确保是同一个session 不是同一个session则不应该进行下一步的处理
            if (exist_session.getId() != null && exist_session.getId().equals(session.getId())) {
                netgates.get(sn).remove(openid);
                log.info("有一网关连接关闭！SN：" + sn + ",当前在线数量为" + netgates.get(sn).keySet().size());
            } else {
                log.info("检测到关闭session异常,程序中断处理,关闭sessionId：" + session.getId() + ",当前实际sessionId:" + exist_session.getId());
            }
        } else {
            log.info("检测到关闭session异常,程序中断处理,系统中未找到对应的session,Sn=" + sn + "openid=" + openid);
        }
    }

    private String getSysDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
}
