package org.neoris.microservices.accounts.infrastructure.input.event.listener;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neoris.microservices.accounts.application.input.port.client.SaveClientServiceInputPort;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.ClientRequestDTO;
import org.neoris.microservices.accounts.infrastructure.input.event.listener.dto.mapper.AccountClientRequestDtoMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessListenerCreateClient {

  private static final String CREATE_QUEUE = "CREATE_CLIENT";
  private final Gson gson;
  private final SaveClientServiceInputPort saveClientServiceAccount;
  private final AccountClientRequestDtoMapper accountClientRequestDtoMapper;

  @RabbitListener(queues = CREATE_QUEUE)
  public void process(String messageEvent, Message message, Channel channel) throws Exception {
    try {
      log.info("ProcessListenerCreateClient processing message: {}", messageEvent);
      ClientRequestDTO requestDTO = gson.fromJson(messageEvent, ClientRequestDTO.class);
      requestDTO.setId(null);
      saveClientServiceAccount.save(accountClientRequestDtoMapper.toEntity(requestDTO));
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (Exception e) {
      log.error("Error processing message: {}", messageEvent, e);
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}