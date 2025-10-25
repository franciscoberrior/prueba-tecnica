package org.neoris.microservices.accounts.infrastructure.input.api.controller.account;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.account.DeleteAccountServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.account.GetAccountServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.account.SaveAccountServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.account.UpdateAccountServiceInputPort;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountRequestDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountResponseDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.request.AccountRequestDTO;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.response.AccountResponseDTO;
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
@RequestMapping("/cuentas")
public class AccountController {

  private final SaveAccountServiceInputPort saveAccountService;
  private final GetAccountServiceInputPort getAccountService;
  private final UpdateAccountServiceInputPort updateAccountService;
  private final DeleteAccountServiceInputPort deleteAccountService;
  private final AccountRequestDtoMapper accountRequestDtoMapper;
  private final AccountResponseDtoMapper accountResponseDtoMapper;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody AccountRequestDTO accountRequest) {
    saveAccountService.save(accountRequestDtoMapper.toEntity(accountRequest),
        accountRequest.getDocumentId());
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody AccountRequestDTO accountRequest) {
    updateAccountService.update(accountRequestDtoMapper.toEntity(accountRequest));
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<AccountResponseDTO> searchById(@RequestParam("accountId") String accoundId) {
    return ResponseEntity.ok(accountResponseDtoMapper.toDto(getAccountService.getById(accoundId)));
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteById(@RequestParam("accountId") String accoundId) {
    deleteAccountService.delete(accoundId);
    return ResponseEntity.ok().build();
  }
}
