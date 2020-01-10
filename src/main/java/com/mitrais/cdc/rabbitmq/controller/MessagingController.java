package com.mitrais.cdc.rabbitmq.controller;

import com.mitrais.cdc.rabbitmq.payload.AccountPayload;
import com.mitrais.cdc.rabbitmq.services.ConsumerServices;
import com.mitrais.cdc.rabbitmq.services.ProducerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class MessagingController {

    @Autowired
    private ProducerServices producerServices;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        try {
            producerServices.sendMessage(message, "mitrais");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Data has been sent successfully....");
    }

    @PostMapping("/send-object")
    public ResponseEntity<String> sendObject(@RequestBody AccountPayload accountPayload){

        producerServices.sendPayload(accountPayload);
        return ResponseEntity.ok("Data has been sent successfully....");
    }
}
