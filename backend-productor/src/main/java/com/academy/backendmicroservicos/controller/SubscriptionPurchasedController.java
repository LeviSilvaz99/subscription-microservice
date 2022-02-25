package com.academy.backendmicroservicos.controller;

import com.academy.backendmicroservicos.constants.RabbitMQContatants;
import com.academy.backendmicroservicos.domain.SubscriptionPurchased;
import com.academy.backendmicroservicos.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchased")
public class SubscriptionPurchasedController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity alteraPurchased(@RequestBody SubscriptionPurchased subscriptionPurchased){
        this.rabbitMQService.enviaMensagem(RabbitMQContatants.FILA_SUBSCRIPTION_PURCHASED, subscriptionPurchased);
        return new ResponseEntity(HttpStatus.OK);
    }
}
