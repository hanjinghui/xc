package com.xuecheng.rabbitMq.Consumer;


import com.rabbitmq.client.Channel;
import com.xuecheng.rabbitMq.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    //监听email队列
   @RabbitListener( queues = {RabbitMqConfig.QUEUE_INFORM_EMAIL})
    public void  receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }

    //监听sms队列
    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFORM_SMS})
    public void  receive_sms(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
}
