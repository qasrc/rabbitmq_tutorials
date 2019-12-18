package lesson1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangzhan
 */
public class Recv {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.");
        DeliverCallback deliverCallback = (consumeTag,delivery)->{
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" Received '" + message + "'");
        };
        channel.basicConsume(Constant.QUEUE_NAME, deliverCallback, consumeTag -> {
        });
    }
}
