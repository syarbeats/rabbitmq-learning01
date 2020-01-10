package com.mitrais.cdc.rabbitmq.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPayload implements Serializable {

    private String username;
    private String accountNo;

}
