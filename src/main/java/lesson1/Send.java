package lesson1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zhangzhan
 */
public class Send {


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
        ) {
            channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 100; i++) {
                String message = "Hello World! index:" + i;
                channel.basicPublish("", Constant.QUEUE_NAME, null, message.getBytes());
                System.out.println(" Sent '" + message + "'");
            }

        } catch (Exception e) {
            System.out.println("发送数据失败：" + e.getMessage());
        }
    }
}
