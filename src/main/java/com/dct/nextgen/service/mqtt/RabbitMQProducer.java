package com.dct.nextgen.service.mqtt;

import com.dct.nextgen.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String routingKey, String message) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.Exchange.DIRECT_EXCHANGE, routingKey, message);
    }
}
