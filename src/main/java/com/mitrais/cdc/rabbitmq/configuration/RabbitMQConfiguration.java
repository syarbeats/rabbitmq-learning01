package com.mitrais.cdc.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${ecom.amqp.rabbit.address}")
    private String rabbitMQAddress;

    @Value("${ecom.amqp.rabbit.username}")
    private String rabbitMQUser;

    @Value("${ecom.amqp.rabbit.password}")
    private String rabbitMQPassword;

    @Value("${ecom.amqp.rabbit.vhost}")
    private String rabbitMQVhost;

    @Value("${ecom.amqp.rabbit.queue}")
    private String rabbitMQQueue;

    @Value("${ecom.amqp.rabbit.object}")
    private String rabbitMQQueue01;

    @Value("${ecom.amqp.rabbit.exchange}")
    private String rabbitMQExchange;

    @Value("${ecom.amqp.rabbit.exchange3}")
    private String rabbitMQExchange3;

    @Bean
    public Queue queue(){
        return new Queue(rabbitMQQueue, true, false, false);
    }

    @Bean
    public Queue queueObject(){
        return new Queue(rabbitMQQueue01, true, false, false);
    }

    @Bean
    public TopicExchange eventBusExchange() {

        return new TopicExchange(rabbitMQExchange, true, false);
    }

    @Bean
    public TopicExchange eventBusExchange3() {

        return new TopicExchange(rabbitMQExchange3, true, false);
    }

    @Bean
    public Binding binding() {

        return BindingBuilder.bind(queue()).to(eventBusExchange()).with("link");
    }

    @Bean
    public Binding bindingObject() {

        return BindingBuilder.bind(queueObject()).to(eventBusExchange3()).with("link-object");
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMQAddress);
        connectionFactory.setUsername(rabbitMQUser);
        connectionFactory.setPassword(rabbitMQPassword);
        connectionFactory.setVirtualHost(rabbitMQVhost);
        connectionFactory.setConnectionTimeout(500000);
        connectionFactory.setRequestedHeartBeat(20);
        return connectionFactory;
    }
}
