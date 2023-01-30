package com.example.renwushu.config.webSocket;

import com.example.renwushu.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Resource
    JwtUtil jwtUtil;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket/{auth}/{pid}/{sn}/{openid}")                          //注册Handler
                .addInterceptors(new WebSocketHandshakeInterceptor(jwtUtil))           //注册Interceptor
                .setAllowedOrigins("*");                                 //注册Interceptor
//     //2.注册SockJS，提供SockJS支持(主要是兼容ie8)
//     String sockjs_url = "/sockjs/socketServer.do";                          //设置sockjs的地址
//     registry.addHandler(netgateHandler, sockjs_url)                         //注册Handler
//         .addInterceptors(new WebSocketHandshakeInterceptor())               //注册Interceptor
//         .withSockJS();                                                      //支持sockjs协议
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(2 * 1024 * 1024);//8192*1024 1024*1024*1024
        container.setMaxBinaryMessageBufferSize(2 * 1024 * 1024);
        container.setAsyncSendTimeout(55000l);
        container.setMaxSessionIdleTimeout(55000l);//心跳
        return container;
    }

    @Bean
    public TextWebSocketHandler webSocketHandler() {
        return new NetgateHandler();
    }
}

