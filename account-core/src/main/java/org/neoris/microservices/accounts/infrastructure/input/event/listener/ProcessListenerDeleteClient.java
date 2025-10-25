package org.neoris.microservices.accounts.infrastructure.input.event.listener;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neoris.microservices.accounts.application.input.port.client.DeleteClientServiceInputPort;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.ClientRequestDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessListenerDeleteClient {

  private static final String DELETE_QUEUE = "DELETE_CLIENT";
  private final Gson gson;
  private final DeleteClientServiceInputPort deleteClientService;

  @RabbitListener(queues = DELETE_QUEUE)
  public void process(String messageEvent, Message message, Channel channel) throws Exception {
    try {
      log.info("ProcessListenerDeleteClient received message: {}", messageEvent);
      ClientRequestDTO requestDTO = gson.fromJson(messageEvent, ClientRequestDTO.class);
      deleteClientService.delete(requestDTO.getId());
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (Exception e) {
      log.error("Error processing delete client message: {}", e.getMessage(), e);
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}