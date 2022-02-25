package com.example.backendmicroservicoconsumer.consumer;

import com.example.backendmicroservicoconsumer.constants.RabbitMQContatants;
import com.example.backendmicroservicoconsumer.domain.SubscriptionCanceled;
import com.example.backendmicroservicoconsumer.repository.SubscriptionCanceledRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionCanceledConsumer {

    @Autowired
    private SubscriptionCanceledRepository subscriptionCanceledRepository;

    @RabbitListener(queues = RabbitMQContatants.FILA_SUBSCRIPTION_CANCELED)
    private void consumidorCanceled(String mensagem) throws JsonProcessingException, InterruptedException {
        SubscriptionCanceled subscriptionCanceled = new ObjectMapper().readValue(mensagem, SubscriptionCanceled.class);

        System.out.println("ID       - "       + subscriptionCanceled.getCode());
        System.out.println("STATUS   - "       + subscriptionCanceled.getStatus());
        System.out.println("USUARIO  - "       + subscriptionCanceled.getUser());
        System.out.println(RabbitMQContatants.FILA_SUBSCRIPTION_CANCELED);
        System.out.println("------------------");
        persisteBancoCanceled(subscriptionCanceled);

    }

    private SubscriptionCanceled persisteBancoCanceled(SubscriptionCanceled subscriptionCanceled){
        return subscriptionCanceledRepository.save(subscriptionCanceled);
    }
}
