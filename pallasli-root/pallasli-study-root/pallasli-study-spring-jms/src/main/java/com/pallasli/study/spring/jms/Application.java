package com.pallasli.study.spring.jms;

import java.io.File;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.FileSystemUtils;

/**
 * @SpringBootApplication is a convenience annotation that adds all of the
 *                        following:
 * 
 *                        ___@Configuration tags the class as a source of bean
 *                        definitions for the application context.
 * 
 *                        ___@EnableAutoConfiguration tells Spring Boot to start
 *                        adding beans based on classpath settings, other beans,
 *                        and various property settings.
 * 
 *                        ___@ComponentScan tells Spring to look for other
 *                        components, configurations, and services in the the
 *                        hello package, allowing it to find the Receiver.
 * 
 * @EnableJms triggers the discovery of methods annotated with @JmsListener,
 *            creating the message listener container under the covers.
 * 
 * 
 * @author lyt1987
 *
 */
@SpringBootApplication
@EnableJms
public class Application {

	// Strictly speaking this bean is not necessary as boot creates a default
	@Bean
	JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	public static void main(String[] args) {
		// Clean out any ActiveMQ data from a previous run
		FileSystemUtils.deleteRecursively(new File("activemq-data"));

		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		// Send a message
		MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("ping!");
			}
		};
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		System.out.println("Sending a new message.");
		jmsTemplate.send("mailbox-destination", messageCreator);
	}

}
