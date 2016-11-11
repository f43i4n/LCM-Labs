package de.ustutt.iaas.lcm.labs.supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import de.ustutt.iaas.lcm.labs.model.AcknowledgeMessage;
import de.ustutt.iaas.lcm.labs.model.SupplyRequest;

import java.lang.reflect.Type;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

@SpringBootApplication
@EnableJms
public class Application {
	
	private static class CustomJackson2MessageConverter extends MappingJackson2MessageConverter {
		
		@Override
		protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
			Type type = AcknowledgeMessage.class;
			if(message.getJMSDestination().toString().equals("topic://SupplyPubSub")) {
				type = SupplyRequest.class;
			}
			
			return TypeFactory.defaultInstance().constructType(type);
		}
		
	}

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new CustomJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean // Extra container factory to handle pub sub
    public JmsListenerContainerFactory<?> pubSubFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
