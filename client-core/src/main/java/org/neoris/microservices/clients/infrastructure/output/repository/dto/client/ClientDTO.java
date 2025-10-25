package org.neoris.microservices.clients.infrastructure.output.repository.dto.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.person.PersonDTO;

@Data
@Entity
@Table(name = "CLIENT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE CLIENT SET status = false WHERE id = ?")
public class ClientDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "client_id", referencedColumnName = "document_id")
  private PersonDTO clientId;
  private String password;
  private Boolean status;
}
