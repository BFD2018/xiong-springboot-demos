package com.xjt;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Properties;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;
    private static Properties properties;

    static {
        //重量级资源  类加载执行之执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.157.135");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("xiong");
        connectionFactory.setPassword("123456");
    }

    //定义提供连接对象的方法
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Channel getChannel() {
        try {
            return getConnection().createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChanel(Channel channel, Connection conn) {
        try {
            if (channel != null) channel.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChanel(Channel channel) {
        try {
            if (channel != null) channel.close();
            if (getConnection() != null) getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        //System.out.println("RabbitMQUtils.getConnection() = " + RabbitMQUtils.getConnection());
    }
}

