package org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.account.AccountDTO;

@Data
@Entity
@Table(name = "MOVEMENT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "date_movement")
  private LocalDateTime dateMovement;
  @Enumerated(EnumType.STRING)
  private MovementTypeEnum type;
  private BigDecimal value;
  private BigDecimal balance;
  @ManyToOne
  @JoinColumn(name = "account_number", referencedColumnName = "number")
  private AccountDTO accountNumber;

}
