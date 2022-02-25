package com.example.backendmicroservicoconsumer.consumer;

import com.example.backendmicroservicoconsumer.constants.RabbitMQContatants;
import com.example.backendmicroservicoconsumer.domain.SubscriptionPurchased;
import com.example.backendmicroservicoconsumer.repository.SubscriptionPurchasedRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPurchasedConsumer {

    @Autowired
    private SubscriptionPurchasedRepository subscriptionPurchasedRepository;

    @RabbitListener(queues = RabbitMQContatants.FILA_SUBSCRIPTION_PURCHASED)
    private void consumidor(String mensagem) throws JsonProcessingException, InterruptedException {
        SubscriptionPurchased subscriptionPurchased = new ObjectMapper().readValue(mensagem, SubscriptionPurchased.class);

        System.out.println("ID      - "       + subscriptionPurchased.getCode());
        System.out.println("STATUS  - "   + subscriptionPurchased.getStatus());
        System.out.println("USUARIO - " + subscriptionPurchased.getUser());
        System.out.println(RabbitMQContatants.FILA_SUBSCRIPTION_PURCHASED);
        System.out.println("------------------");
        peristeBancoPurchased(subscriptionPurchased);

    }

    private SubscriptionPurchased peristeBancoPurchased(SubscriptionPurchased subscriptionPurchased){
        return subscriptionPurchasedRepository.save(subscriptionPurchased);
    }

}
