/**
 * 
 */
package com.test.message.config;

/**
 * @author Kusha
 *
 */
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    private static final String EXCHANGE_NAME = "message.exchange";
    private static final String DL_EXCHANGE_NAME = "message.exchange.dl";
    private static final String MESSAGE_QUEUE = "message.queue";
    private static final String DL_MESSAGE_QUEUE = "message.queue";


    @Bean
    Exchange messageExchange() {
    	return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    
    @Bean
    Queue messageQueue() {
        return QueueBuilder.durable(MESSAGE_QUEUE)
        		.withArgument("x-dead-letter-exchange", messageDLExchange().getName()).build();
    }
    
    @Bean
    Binding messageBinding() {
        return BindingBuilder.bind(messageQueue()).to(messageExchange()).with("#").noargs();
    }
    
    @Bean
    Exchange messageDLExchange() {
    	return ExchangeBuilder.topicExchange(DL_EXCHANGE_NAME).durable(true).build();
    }
    
    @Bean
    Queue messageDLQueue() {
        return QueueBuilder.durable(DL_MESSAGE_QUEUE)
        		.withArgument("x-dead-letter-exchange", messageExchange().getName()).build();
    }
    
    @Bean
    Binding messageDLBinding() {
        return BindingBuilder.bind(messageDLQueue()).to(messageDLExchange()).with("#").noargs();
    }

}
