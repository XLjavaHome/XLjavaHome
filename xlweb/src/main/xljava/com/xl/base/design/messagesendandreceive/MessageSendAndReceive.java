package com.xl.base.design.messagesendandreceive;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * Created with 徐立.生产者消费者模式
 *
 * @author: 徐立
 * @Date: 2019-01-15
 * @Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
public class MessageSendAndReceive {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = factory.createConnection();
        connection.start();
        Queue queue = new ActiveMQQueue("testQueue");
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Message message = session.createTextMessage("Hello JMS!");
        MessageProducer producer = session.createProducer(queue);
        producer.send(message);
        System.out.println("Send Message Completed!");
        MessageConsumer comsumer = session.createConsumer(queue);
        Message recvMessage = comsumer.receive();
        System.out.println(((TextMessage) recvMessage).getText());
    }
}
