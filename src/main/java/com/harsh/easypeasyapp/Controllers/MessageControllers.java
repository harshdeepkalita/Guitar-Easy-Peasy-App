package com.harsh.easypeasyapp.Controllers;

import com.harsh.easypeasyapp.DTOs.MessageDto;
import com.harsh.easypeasyapp.ServicesRabbitMQ.PublishMessage;
import com.harsh.easypeasyapp.ServicesRabbitMQ.RabbitMQHelper;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageControllers {

    private final RabbitMQHelper rabbitMQHelper;
    private final PublishMessage publishMessage;


    public MessageControllers(RabbitMQHelper rabbitMQHelper, PublishMessage publishMessage) {
        this.rabbitMQHelper = rabbitMQHelper;
        this.publishMessage = publishMessage;
    }

    @PostMapping("/chat")
    public void  sendMessage(@RequestBody MessageDto messageDto, @RequestParam Integer recieverId,@RequestParam Integer senderId,@RequestParam String senderType){
        String queueName;
        String exchangeName = "directExchange";

        if ("student".equals(senderType)){
            queueName = "tutor_"+recieverId+"-messages-queue";
        } else {
            queueName = "student_"+recieverId+"-messages-queue";
        }
        String routingKey = senderId + "_to_" + recieverId;
        rabbitMQHelper.createBinding(routingKey,queueName,exchangeName);

        publishMessage.sendMessage(messageDto,exchangeName,routingKey);
    }


}
