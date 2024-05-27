package com.cloud.webserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class WebSocketController {
    @Resource
    private SimpMessagingTemplate template;

    @MessageMapping("/sendToUser")
    public void sendToUser(Map<String, String> params) {
        String fromUserId = params.get("fromUserId");
        String toUserId = params.get("toUserId");
        String msg = "来自用户" + fromUserId + "的消息:" + params.get("msg");

        String destination = "/queue/user" + toUserId;
        template.convertAndSend(destination, msg);
    }

    @GetMapping("/sendToAll")
    @MessageMapping("/sendToAll")
    public void sendToAll(Map<String, String> params) {
        String fromUserId = params.get("fromUserId");
        String msg = "来自用户" + fromUserId + "的广播:" + params.get("msg");

        String destination = "/topic/chat";
        template.convertAndSend(destination, msg);
    }

}

