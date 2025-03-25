package com.dct.nextgen.web.mqtt;

import com.dct.nextgen.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("bindingQueues") // Make sure queues are created before Consumer is launched
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConstants.Queue.NOTIFICATION)
    public void receiveMessage(String message) {
        System.out.println("Received from cloud: " + message);
    }
}
