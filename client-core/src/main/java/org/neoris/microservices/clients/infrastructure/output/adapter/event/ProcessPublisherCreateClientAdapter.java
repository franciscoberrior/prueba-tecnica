package org.neoris.microservices.clients.infrastructure.output.adapter.event;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neoris.microservices.clients.application.output.port.event.ProcessPublisherService;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;
import org.neoris.microservices.clients.domain.model.Client;
import org.neoris.microservices.clients.infrastructure.input.api.dto.mapper.ClientResponseDtoMapper;
import org.neoris.microservices.clients.infrastructure.input.api.dto.response.ClientResponseDTO;
import org.neoris.microservices.clients.infrastructure.output.event.publisher.ProcessPublisher;
import org.neoris.microservices.clients.shared.event.dto.Event;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ProcessPublisherCreateClientAdapter implements ProcessPublisherService {

  private static final String EVENT_QUEUE = "CREATE_CLIENT";
  private final ClientResponseDtoMapper clientResponseDtoMapper;
  private final ProcessPublisher<String> processPublisher;
  private final Gson gson;

  @Override
  public ProcessClientTypeEnum type() {
    return ProcessClientTypeEnum.CREATE;
  }

  @Override
  public void execute(Client client) {
    processPublisher.execute(build(clientResponseDtoMapper.toDto(client)));
  }

  private Event<String> build(ClientResponseDTO requestDTO) {
    return new Event<>(EVENT_QUEUE, gson.toJson(requestDTO));
  }
}
