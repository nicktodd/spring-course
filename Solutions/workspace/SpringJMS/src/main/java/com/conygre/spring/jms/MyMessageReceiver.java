package com.conygre.spring.jms;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
 
@Service
/**
 * Listener Implement Spring SessionAwareMessageListener Interface
 *
 */
public class MyMessageReceiver implements SessionAwareMessageListener<TextMessage> {
 
  @Override
  public void onMessage(TextMessage message, Session session) throws JMSException {
    // This is the received message
    System.out.println("Receive: "+message.getText());
  }
}