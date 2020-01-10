package com.mitrais.cdc.rabbitmq.services;

import com.mitrais.cdc.rabbitmq.payload.AccountPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServices {

    @RabbitListener(queues = "mitrais")
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        log.info("Received message: {}, CorellationID: {} ", messageBody, message.getMessageProperties().getCorrelationId());
    }

    @RabbitListener(queues = "mitrais01")
    public void onMessage(AccountPayload message) {
        log.info("Received message: {} ", message.getUsername());
    }
}
