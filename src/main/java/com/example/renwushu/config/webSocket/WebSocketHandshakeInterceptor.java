package com.example.renwushu.config.webSocket;

import com.example.renwushu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Describes: WebSocket握手拦截器
 * Auth: Eric
 */
@Slf4j
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private JwtUtil jwtUtil;

    public WebSocketHandshakeInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

    }

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        log.info("Before Handshake！");
        if (request instanceof ServletServerHttpRequest) {
            String path = request.getURI().getPath();

            String[] params = getParams(path);
            if (requestIsValid(params)) {
                attributes.put("WEBSOCKET_AUTH", params[0]);//鉴权
                attributes.put("WEBSOCKET_PID", params[1]);//设备产品ID
                attributes.put("WEBSOCKET_SN", params[2]);//设备序列号
                attributes.put("WEBSOCKET_OPENID", params[3]);//用户id
                attributes.put("WEBSOCKET_FIRSTONE", "yes");
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("After Handshake！");
        if (e != null) e.printStackTrace();
    }

    private boolean requestIsValid(String[] params) {
        //在这里可以写上具体的鉴权逻辑
        if (params != null && params.length > 0) {
            Claims claim = jwtUtil.getClaimByToken(params[0]);
            if (claim == null) {
                log.info("token异常！");
                return false;
            }
            if (jwtUtil.isTokenExpired(claim)) {
                log.info("token已过期！");
                return false;
            }
            return true;
        }
        return false;
    }

    private String[] getParams(String url) {
        if (url.indexOf("websocket") != -1 && url.split("/").length > 6) {
            url = url.substring(url.indexOf("websocket/"));
            url = url.replace("websocket/", "");
            return url.split("/");
        }

        return new String[0];
    }

}

