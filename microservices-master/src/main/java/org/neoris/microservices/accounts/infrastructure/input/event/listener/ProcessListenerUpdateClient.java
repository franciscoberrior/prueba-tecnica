package org.neoris.microservices.accounts.infrastructure.input.event.listener;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neoris.microservices.accounts.application.input.port.client.UpdateClientServiceInputPort;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.ClientRequestDTO;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.mapper.AccountClientRequestDtoMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessListenerUpdateClient {

  public static final String UPDATE_QUEUE = "UPDATE_CLIENT";
  private final Gson gson;
  private final UpdateClientServiceInputPort updateClientService;
  private final AccountClientRequestDtoMapper accountClientRequestDtoMapper;

  @RabbitListener(queues = UPDATE_QUEUE)
  public void process(String messageEvent, Message message, Channel channel) throws Exception {
    try {
      log.info("ProcessListenerUpdateClient received message: {}", messageEvent);
      ClientRequestDTO requestDTO = gson.fromJson(messageEvent, ClientRequestDTO.class);
      updateClientService.update(accountClientRequestDtoMapper.toEntity(requestDTO));
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (Exception e) {
      log.error("Error processing message in ProcessListenerUpdateClient: {}", e.getMessage(), e);
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}