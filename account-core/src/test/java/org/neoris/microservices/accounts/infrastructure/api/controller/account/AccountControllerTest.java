package org.neoris.microservices.accounts.infrastructure.api.controller.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.neoris.microservices.accounts.application.output.port.account.AccountDeleteService;
import org.neoris.microservices.accounts.application.output.port.account.AccountGetService;
import org.neoris.microservices.accounts.application.output.port.account.AccountSaveService;
import org.neoris.microservices.accounts.application.output.port.account.AccountUpdateService;
import org.neoris.microservices.accounts.application.services.account.DeleteAccountService;
import org.neoris.microservices.accounts.application.services.account.GetAccountService;
import org.neoris.microservices.accounts.application.services.account.SaveAccountService;
import org.neoris.microservices.accounts.application.services.account.UpdateAccountService;
import org.neoris.microservices.accounts.application.services.client.GetClientService;
import org.neoris.microservices.accounts.infrastructure.input.api.controller.account.AccountController;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountRequestDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountRequestDtoMapperImpl;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountResponseDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.AccountResponseDtoMapperImpl;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.request.AccountRequestDTO;
import org.neoris.microservices.accounts.infrastructure.output.adapter.account.AccountAdapter;
import org.neoris.microservices.accounts.infrastructure.output.adapter.client.AccountClientAdapter;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.AccountDTO;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.AccountRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.mapper.AccountDtoMapper;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.mapper.AccountDtoMapperImpl;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.AccountClientRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.ClientDTO;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.mapper.AccountClientDtoMapper;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.mapper.AccountClientDtoMapperImpl;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.person.AccountPersonRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.person.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {


  @InjectMocks
  private AccountController accountController;

  private SaveAccountService saveAccountService;
  private GetAccountService getAccountService;
  private UpdateAccountService updateAccountService;
  private DeleteAccountService deleteAccountService;
  private AccountRequestDtoMapper accountRequestDtoMapper;
  private AccountResponseDtoMapper accountResponseDtoMapper;

  private AccountSaveService accountSaveService;
  private GetClientService getClientService;
  private AccountGetService accountGetService;
  private AccountUpdateService accountUpdateService;
  private AccountDeleteService accountDeleteService;

  private AccountClientDtoMapper accountClientDtoMapper;

  private AccountDtoMapper accountDtoMapper;

  @Mock
  private AccountRepository accountRepository;
  @Mock
  private AccountClientRepository accountClientRepository;
  @Mock
  private AccountPersonRepository accountPersonRepository;

  private MockMvc mockMvc;

  private Gson gson = new Gson();

  @BeforeEach
  void setUp() {

    accountClientDtoMapper = new AccountClientDtoMapperImpl();
    accountDtoMapper = new AccountDtoMapperImpl(accountClientDtoMapper);

    var accountAdapter = new AccountAdapter(accountRepository, accountDtoMapper);
    var accountClientAdapter = new AccountClientAdapter(accountClientRepository,
        accountPersonRepository);

    accountSaveService = accountAdapter;
    getClientService = new GetClientService(accountClientAdapter);
    accountGetService = accountAdapter;
    accountUpdateService = accountAdapter;
    accountDeleteService = accountAdapter;

    saveAccountService = new SaveAccountService(accountSaveService, getClientService);
    getAccountService = new GetAccountService(accountGetService);
    updateAccountService = new UpdateAccountService(accountUpdateService);
    deleteAccountService = new DeleteAccountService(accountDeleteService);
    accountRequestDtoMapper = new AccountRequestDtoMapperImpl();
    accountResponseDtoMapper = new AccountResponseDtoMapperImpl();

    accountController = new AccountController(saveAccountService, getAccountService,
        updateAccountService, deleteAccountService, accountRequestDtoMapper,
        accountResponseDtoMapper);

    mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

  }

  private String jsonRequest(String path) {
    Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(path));
    AccountRequestDTO requestDTO = gson.fromJson(reader, AccountRequestDTO.class);
    return gson.toJson(requestDTO);
  }


  @Test
  void save() throws Exception {
    when(accountClientRepository.findByDocumentId(anyString()))
        .thenReturn(
            Optional.ofNullable(
                ClientDTO.builder()
                    .id(1L)
                    .password("password")
                    .status(Boolean.TRUE)
                    .clientId(
                        PersonDTO.builder()
                            .documentId("12345")
                            .address("address")
                            .age(30)
                            .gender("M")
                            .name("Juan")
                            .phone("123456789")
                            .build()
                    )
                    .build()
            )
        );

    String request = jsonRequest("/478758.json");
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/cuentas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }

  @Test
  void update() throws Exception {
    String request = jsonRequest("/478758.json");
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/cuentas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }

  @Test
  void searchById() throws Exception {
    when(accountRepository.findById(anyString()))
        .thenReturn(Optional.ofNullable(AccountDTO.builder().number("478758").build()));
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/cuentas")
        .queryParam("accountId", "478758")
        .contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }

  @Test
  void deleteById() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/cuentas")
        .queryParam("accountId", "478758")
        .contentType(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }
}