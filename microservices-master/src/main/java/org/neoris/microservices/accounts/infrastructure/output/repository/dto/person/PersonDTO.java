package org.neoris.microservices.accounts.infrastructure.output.repository.dto.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

  @Id
  @Column(name = "document_id")
  private String documentId;
  private String name;
  private String gender;
  private Integer age;
  private String address;
  private String phone;
}
