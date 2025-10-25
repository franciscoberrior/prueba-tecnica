package org.neoris.microservices.clients.infrastructure.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue createClientQueue() {
        return new Queue("CREATE_CLIENT", true); // true = durable
    }

    @Bean
    public Queue deleteClientQueue() {
        return new Queue("DELETE_CLIENT", true); // true = durable
    }

    @Bean
    public Queue updateClientQueue() {
        return new Queue("UPDATE_CLIENT", true); // true = durable
    }
}