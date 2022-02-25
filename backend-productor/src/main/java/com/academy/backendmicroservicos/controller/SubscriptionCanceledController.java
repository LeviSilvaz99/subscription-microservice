package com.academy.backendmicroservicos.controller;

import com.academy.backendmicroservicos.constants.RabbitMQContatants;
import com.academy.backendmicroservicos.domain.SubscriptionCanceled;
import com.academy.backendmicroservicos.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/canceled")
public class SubscriptionCanceledController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity alterarCanceled(@RequestBody SubscriptionCanceled subscriptionCanceled){
        this.rabbitMQService.enviaMensagem(RabbitMQContatants.FILA_SUBSCRIPTION_CANCELED, subscriptionCanceled);
        return new ResponseEntity(HttpStatus.OK);
    }
}
