package org.neoris.microservices.clients.infrastructure.output.event.publisher;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.neoris.microservices.clients.shared.event.dto.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContextPublisher {

  private final RabbitTemplate rabbitTemplate;

  public <T> void publish(final Event<T> event) {
    HashMap<String, String> headers = new HashMap<>();
    headers.putAll(event.getHeaders());
    headers.put("CONTEXT", "INTERNAL");
    rabbitTemplate.convertAndSend(event.getType(), event.getData());
  }

}