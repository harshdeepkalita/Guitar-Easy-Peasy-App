package com.harsh.easypeasyapp.ServicesRabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQHelper {

    private final RabbitAdmin rabbitAdmin;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQHelper.class);

    public RabbitMQHelper(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public Queue createQueue(String queueName){
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    public DirectExchange createDirectExchange(String exchangeName){
        DirectExchange directExchange = new DirectExchange(exchangeName);
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    public void createBinding(String routingKey,String queueName,String exchangeName){

        rabbitAdmin.declareBinding(BindingBuilder.bind(createQueue(queueName)).
                to(createDirectExchange(exchangeName)).
                with(routingKey));
        LOGGER.info(String.format("Binding defined %s - %s - %s",exchangeName,queueName,routingKey));
    }

}
