package com.example.backendmicroservicoconsumer.consumer;

import com.example.backendmicroservicoconsumer.constants.RabbitMQContatants;
import com.example.backendmicroservicoconsumer.domain.SubscriptionRestarted;
import com.example.backendmicroservicoconsumer.repository.SubscriptionRestartedRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRestartedConsumer {

    @Autowired
    private SubscriptionRestartedRepository subscriptionRestartedRepository;

    @RabbitListener(queues = RabbitMQContatants.FILA_SUBSCRIPTION_RESTARTED)
    private void consumidorRestarted(String mensagem) throws JsonProcessingException, InterruptedException{
        SubscriptionRestarted subscriptionRestarted = new ObjectMapper().readValue(mensagem, SubscriptionRestarted.class);

        System.out.println("ID       - "       + subscriptionRestarted.getCode());
        System.out.println("STATUS   - "       + subscriptionRestarted.getStatus());
        System.out.println("USUARIO  - "       + subscriptionRestarted.getUser());
        System.out.println(RabbitMQContatants.FILA_SUBSCRIPTION_RESTARTED);
        System.out.println("------------------");
        persisteBancoRestarted(subscriptionRestarted);

    }

    private SubscriptionRestarted persisteBancoRestarted(SubscriptionRestarted subscriptionRestarted){
        return subscriptionRestartedRepository.save(subscriptionRestarted);
    }
}

