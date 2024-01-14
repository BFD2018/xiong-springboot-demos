package com.xjt.helloworld_1;

import com.sun.jdi.connect.spi.Connection;
import com.xjt.RabbitMQUtils;

import java.io.IOException;
import java.nio.channels.Channel;

public class Consumer {
    public static void main(String[] args) throws IOException {
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数1:  队列名称 如果队列不存在自动创建
        //参数2:  用来定义队列特性是否要持久化 true 持久化队列   false 不持久化
        //参数3:  exclusive 是否独占队列  true 独占队列   false  不独占
        //参数4:  autoDelete: 是否在消费完成后自动删除队列  true 自动删除  false 不自动删除
        //参数5:  额外附加参数
        channel.queueDeclare("hello", true, false, false, null);

        //消费消息
        //参数1：消费哪个队列的消息，队列名称
        //参数2：消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            //重写处理消息的方法
            //最后一个参数是 队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(consumerTag + "===========>" + new String(body));
            }
        });

        //RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}

