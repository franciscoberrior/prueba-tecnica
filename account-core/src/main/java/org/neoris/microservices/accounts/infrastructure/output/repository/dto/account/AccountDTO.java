package org.neoris.microservices.accounts.infrastructure.output.repository.dto.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.neoris.microservices.accounts.domain.enums.AccountTypeEnum;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.client.ClientDTO;

@Data
@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ACCOUNT SET status = false WHERE number = ?")
public class AccountDTO {

  @Id
  private String number;
  @Enumerated(EnumType.STRING)
  private AccountTypeEnum type;
  @Column(name = "initial_balance")
  private BigDecimal initialBalance;
  private Boolean status;
  @ManyToOne
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private ClientDTO clientId;
}
