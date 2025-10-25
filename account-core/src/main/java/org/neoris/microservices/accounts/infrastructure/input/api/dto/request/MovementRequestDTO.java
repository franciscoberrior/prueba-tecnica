package org.neoris.microservices.accounts.infrastructure.input.api.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementRequestDTO {

  private Long id;
  private MovementTypeEnum type;
  private BigDecimal value;
  private String accountNumber;

}
