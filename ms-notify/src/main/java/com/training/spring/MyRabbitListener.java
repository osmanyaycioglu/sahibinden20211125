package com.training.spring;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-queue", durable = "true", autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "sms-notification"))
    public void handleSMSEvent(final SendMessage str) {
        System.out.println("SMS Received : " + str);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "mail-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "mail-notification"))
    public void handleMailEvent(final SendMessage str) {
        System.out.println("Mail Received : " + str);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-topic-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-topic-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "rest.order.sms.#"))
    public void handleSMSTopicEvent(final SendMessage str) {
        System.out.println("SMS  Topic Received : " + str);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-x-queue",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notfication-topic-exchange",
                                                                  autoDelete = "false",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "rest.#"))
    public void handleXTopicEvent(final SendMessage str) {
        System.out.println("X Topic Received : " + str);
    }


}
