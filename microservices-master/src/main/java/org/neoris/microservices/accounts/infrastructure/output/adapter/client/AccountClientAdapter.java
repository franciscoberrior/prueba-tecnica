package org.neoris.microservices.accounts.infrastructure.output.adapter.client;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.output.port.client.ClientDeleteService;
import org.neoris.microservices.accounts.application.output.port.client.ClientGetService;
import org.neoris.microservices.accounts.application.output.port.client.ClientSaveService;
import org.neoris.microservices.accounts.application.output.port.client.ClientUpdateService;
import org.neoris.microservices.accounts.domain.model.Client;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.AccountClientRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.ClientDTO;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.mapper.AccountClientDtoMapper;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.person.AccountPersonRepository;
import org.neoris.microservices.accounts.shared.exception.infrastructure.adapter.ClientNotFoundException;
import org.neoris.microservices.accounts.shared.exception.infrastructure.adapter.PersonFoundException;
import org.neoris.microservices.accounts.shared.exception.infrastructure.adapter.PersonNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountClientAdapter implements ClientSaveService, ClientGetService,
    ClientUpdateService,
    ClientDeleteService {

  private final AccountClientRepository accountClientRepository;
  private final AccountPersonRepository accountPersonRepository;

  @Override
  public void delete(Long id) {
    accountClientRepository.deleteById(id);
  }

  @Override
  public Client get(String id) {
    return AccountClientDtoMapper.INSTANCE.toEntity(
        accountClientRepository.findByDocumentId(id)
            .orElseThrow(ClientNotFoundException::new));
  }

  @Override
  public void save(Client client) {
    ClientDTO clientDTO = AccountClientDtoMapper.INSTANCE.toDto(client);
    if (accountPersonRepository.findById(client.getDocumentId()).isPresent()) {
      throw new PersonFoundException();
    }
    accountPersonRepository.save(clientDTO.getClientId());
    accountClientRepository.save(clientDTO);
  }

  @Override
  public void update(Client client) {
    ClientDTO clientDTO = AccountClientDtoMapper.INSTANCE.toDto(client);
    if (accountPersonRepository.findById(client.getDocumentId()).isPresent()) {
      Optional<ClientDTO> clientRes = accountClientRepository.findById(client.getId());
      if (clientRes.isPresent()) {
        accountPersonRepository.save(clientDTO.getClientId());
        clientDTO.setStatus(clientRes.get().getStatus());
        accountClientRepository.save(clientDTO);
      } else {
        throw new ClientNotFoundException();
      }
    } else {
      throw new PersonNotFoundException();
    }
  }
}
