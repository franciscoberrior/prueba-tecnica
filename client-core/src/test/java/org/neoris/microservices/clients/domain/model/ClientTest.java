package org.neoris.microservices.clients.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientTest {

  private Client client;

  @BeforeEach
  void init() {
    this.client = Client.builder()
        .id(1L)
        .password("123")
        .status(true)
        .age(24)
        .address("Otavalo sn y principal")
        .documentId("1143364975")
        .gender("MASCULINO")
        .name("Jose Lema")
        .phone("098254785")
        .build();
  }

  @Test
  void getId() {
    Long id = client.getId();
    Assertions.assertEquals(1L, id);
  }

  @Test
  void getPassword() {
    String password = client.getPassword();
    Assertions.assertEquals("123", password);
  }

  @Test
  void getStatus() {
    boolean status = client.getStatus();
    Assertions.assertEquals(true, status);
  }

  @Test
  void setId() {
    client.setId(1L);
    Assertions.assertEquals(1L, client.getId());
  }

  @Test
  void setPassword() {
    client.setPassword("321");
    Assertions.assertEquals("321", client.getPassword());
  }

  @Test
  void setStatus() {
    client.setStatus(false);
    Assertions.assertEquals(false, client.getStatus());
  }
}