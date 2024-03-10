package com.harsh.easypeasyapp.ServicesRabbitMQ;

import com.harsh.easypeasyapp.DTOs.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishMessage.class);

    private  final RabbitTemplate rabbitTemplate;

    public PublishMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDto messageDto,String exchange,String routingKey){
        LOGGER.info(String.format("Json message sent  -> %s",messageDto.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,messageDto);
    }

}
