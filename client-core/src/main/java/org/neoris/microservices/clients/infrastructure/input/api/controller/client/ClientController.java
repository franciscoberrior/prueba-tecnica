package org.neoris.microservices.clients.infrastructure.input.api.controller.client;

import lombok.AllArgsConstructor;
import org.neoris.microservices.clients.application.input.port.DeleteClientServiceInputPort;
import org.neoris.microservices.clients.application.input.port.GetClientServiceInputPort;
import org.neoris.microservices.clients.application.input.port.SaveClientServiceInputPort;
import org.neoris.microservices.clients.application.input.port.UpdateClientServiceInputPort;
import org.neoris.microservices.clients.infrastructure.input.api.dto.mapper.ClientRequestDtoMapper;
import org.neoris.microservices.clients.infrastructure.input.api.dto.mapper.ClientResponseDtoMapper;
import org.neoris.microservices.clients.infrastructure.input.api.dto.mapper.ClientUpdateRequestDtoMapper;
import org.neoris.microservices.clients.infrastructure.input.api.dto.request.ClientRequestDTO;
import org.neoris.microservices.clients.infrastructure.input.api.dto.request.ClientUpdateRequestDto;
import org.neoris.microservices.clients.infrastructure.input.api.dto.response.ClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClientController {

  private final SaveClientServiceInputPort saveClientService;
  private final GetClientServiceInputPort getClientService;
  private final UpdateClientServiceInputPort updateClientService;
  private final DeleteClientServiceInputPort deleteClientService;
  private final ClientRequestDtoMapper clientRequestDtoMapper;
  private final ClientResponseDtoMapper clientResponseDtoMapper;
  private final ClientUpdateRequestDtoMapper clientUpdateRequestDtoMapper;

  @PostMapping
  public ResponseEntity<ClientResponseDTO> save(@RequestBody ClientRequestDTO clientRequestDTO) {
    return ResponseEntity.ok(clientResponseDtoMapper.toDto(
            saveClientService.save(clientRequestDtoMapper.toEntity(clientRequestDTO))));
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody ClientUpdateRequestDto clientRequestDTO) {
    updateClientService.update(clientUpdateRequestDtoMapper.toEntity(clientRequestDTO));
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<ClientResponseDTO> searchById(@RequestParam("clientId") String clientId) {
    return ResponseEntity.ok(clientResponseDtoMapper.toDto(getClientService.getById(clientId)));
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteById(@RequestParam("clientId") String clientId) {
    deleteClientService.delete(clientId);
    return ResponseEntity.ok().build();
  }
}
