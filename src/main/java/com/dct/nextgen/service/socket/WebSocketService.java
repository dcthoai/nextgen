package com.dct.nextgen.service.socket;

import com.dct.nextgen.constants.WebSocketConstants;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notify(String message) {
        sendMessage(WebSocketConstants.Topic.NOTIFICATION, message);
    }

    public void sendMessage(String topic, String message) {
        this.messagingTemplate.convertAndSend(topic, message);
    }
}
