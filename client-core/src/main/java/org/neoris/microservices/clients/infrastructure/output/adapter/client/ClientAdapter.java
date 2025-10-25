package org.neoris.microservices.clients.infrastructure.output.adapter.client;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.neoris.microservices.clients.application.output.port.client.ClientDeleteService;
import org.neoris.microservices.clients.application.output.port.client.ClientGetService;
import org.neoris.microservices.clients.application.output.port.client.ClientSaveService;
import org.neoris.microservices.clients.application.output.port.client.ClientUpdateService;
import org.neoris.microservices.clients.domain.model.Client;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.client.ClientDTO;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.client.ClientRepository;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.client.mapper.ClientDtoMapper;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.person.PersonRepository;
import org.neoris.microservices.clients.shared.exception.infrastructure.adapter.ClientNotFoundException;
import org.neoris.microservices.clients.shared.exception.infrastructure.adapter.PersonFoundException;
import org.neoris.microservices.clients.shared.exception.infrastructure.adapter.PersonNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientAdapter implements ClientSaveService, ClientGetService, ClientUpdateService,
    ClientDeleteService {

  private final ClientRepository clientRepository;
  private final PersonRepository personRepository;

  @Override
  public void delete(Long id) {
    clientRepository.deleteById(id);
  }

  @Override
  public Client getByDocumentId(String id) {
    return ClientDtoMapper.INSTANCE.toEntity(clientRepository.findByDocumentId(id).orElse(null));
  }

  @Override
  public Client save(Client client) {
    ClientDTO clientDTO = ClientDtoMapper.INSTANCE.toDto(client);
    if (personRepository.findById(client.getDocumentId()).isPresent()) {
      throw new PersonFoundException();
    }
    personRepository.save(clientDTO.getClientId());
    return ClientDtoMapper.INSTANCE.toEntity(clientRepository.save(clientDTO));
  }

  @Override
  public Client update(Client client) {
    ClientDTO clientDTO = ClientDtoMapper.INSTANCE.toDto(client);
    if (personRepository.findById(clientDTO.getClientId().getDocumentId()).isPresent()) {
      Optional<ClientDTO> clientRes = clientRepository.findById(client.getId());
      if (clientRes.isPresent()) {
        personRepository.save(clientDTO.getClientId());
        clientDTO.setStatus(clientRes.get().getStatus());
        return ClientDtoMapper.INSTANCE.toEntity(clientRepository.save(clientDTO));
      } else {
        throw new ClientNotFoundException();
      }
    } else {
      throw new PersonNotFoundException();
    }
  }
}
