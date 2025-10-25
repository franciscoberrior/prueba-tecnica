package org.neoris.microservices.clients.infrastructure.output.event.publisher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neoris.microservices.clients.shared.event.dto.Event;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProcessPublisher<T> {

  private final ContextPublisher contextPublisher;

  @Async
  public void execute(Event<T> event) {
    contextPublisher.publish(event);
  }
}
