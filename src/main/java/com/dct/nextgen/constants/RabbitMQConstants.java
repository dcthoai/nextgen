package com.dct.nextgen.constants;

public interface RabbitMQConstants {

    interface Queue {
        String NOTIFICATION = "notification.queue";
    }

    interface RoutingKey {
        String NOTIFICATION = "notification.routingKey";
    }

    interface Exchange {
        String DIRECT_EXCHANGE = "amq.direct";
    }
}
