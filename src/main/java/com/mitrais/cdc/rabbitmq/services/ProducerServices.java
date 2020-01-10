package com.mitrais.cdc.rabbitmq.services;

import com.mitrais.cdc.rabbitmq.payload.AccountPayload;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class ProducerServices {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String payload, String queue) throws IOException, TimeoutException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId(java.util.UUID.randomUUID().toString());
        Message message = new Message(payload.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("mitrais2", "link", message);
        log.info("message has been sent...with CorrelationID: {}", message.getMessageProperties().getCorrelationId());
    }

    public void sendPayload(AccountPayload accountPayload){
        rabbitTemplate.convertAndSend("mitrais3", "link-object", accountPayload);
        log.info("payload has been sent...");
    }
}
