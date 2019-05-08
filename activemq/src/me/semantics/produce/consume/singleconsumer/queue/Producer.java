package me.semantics.produce.consume.singleconsumer.queue;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.

/**
 * @author Levi
 * 
 */
public class Producer {

    /**
     * @param args
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("test.queue");
        MessageProducer producer = session.createProducer(queue);
        // producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // producer.setPriority(0);
        // producer.setTimeToLive(10 * 60 * 1000);

        String msg = "hello coonsumer!";
        TextMessage message = session.createTextMessage(msg);
        message.setStringProperty("ECX_MESSAGE_STANDARD", "W3C");
        producer.send(message);
        session.commit();
        
        System.out.println("sent " + message.getText());

        session.close();
        connection.close();
    }

}
