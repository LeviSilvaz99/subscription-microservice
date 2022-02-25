package com.academy.backendmicroservicos.connections;

import com.academy.backendmicroservicos.constants.RabbitMQContatants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false,false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    //relacionamento entre fila e exchange
    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //fila que queremos adicionar
    @PostConstruct
    private void adiciona(){
        Queue filaPurchased = this.fila(RabbitMQContatants.FILA_SUBSCRIPTION_PURCHASED);
        Queue filaCanceled  = this.fila(RabbitMQContatants.FILA_SUBSCRIPTION_CANCELED);
        Queue filaRestarted = this.fila(RabbitMQContatants.FILA_SUBSCRIPTION_RESTARTED);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoPurchased = this.relacionamento(filaPurchased, troca);
        Binding ligacaoCanceled  = this.relacionamento(filaCanceled, troca);
        Binding ligacaoRestarted = this.relacionamento(filaRestarted, troca);

        //criando as filas no rabbitmq
        this.amqpAdmin.declareQueue(filaPurchased);
        this.amqpAdmin.declareQueue(filaCanceled);
        this.amqpAdmin.declareQueue(filaRestarted);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoPurchased);
        this.amqpAdmin.declareBinding(ligacaoCanceled);
        this.amqpAdmin.declareBinding(ligacaoRestarted);

    }
}
