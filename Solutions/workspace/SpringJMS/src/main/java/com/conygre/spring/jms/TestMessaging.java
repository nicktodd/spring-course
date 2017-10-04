package com.conygre.spring.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMessaging {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        
	    // get bean from context
	    MyMessageSender jmsMessageSender = (MyMessageSender)ctx.getBean("myMessageSender");
	         
	    // send to a code specified destination
	    Queue queue = new ActiveMQQueue("queue/testQueue");
	    jmsMessageSender.send(queue, "Message for a queue");

	    try {
	    	// give time for the receiver to get the messages
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // close spring application context
	    ((ClassPathXmlApplicationContext)ctx).close();
	}

}