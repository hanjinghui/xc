package com.rabbitMq.producerTest;

import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitMq测试代码
 */
public class Producer01 {

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("loclhoust");
        factory.setPort(5672);   //设置端口
        factory.setUsername("guest");  //设置用户名
        factory.setPassword("guest");  //设置密码
        factory.setVirtualHost("/");  //rabbitMq的默认虚拟机名称是"/" 虚拟机想当是一个独立的服务器

    }
}
