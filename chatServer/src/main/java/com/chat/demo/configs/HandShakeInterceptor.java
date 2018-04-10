package com.chat.demo.configs;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");
//        HttpServletRequest hsr = (HttpServletRequest)request;
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession();
        System.err.println(request.getHeaders());
        attributes.put("sessionId",session.getId());
        return true;
    }
}
