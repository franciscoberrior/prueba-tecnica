package org.neoris.microservices.accounts.application.services.movement.types;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.services.account.GetAccountService;
import org.neoris.microservices.accounts.application.services.movement.GetMovementService;
import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;
import org.neoris.microservices.accounts.domain.model.Account;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.shared.exception.domain.InvalidMovementException;
import org.neoris.microservices.accounts.shared.exception.domain.InvalidValueMovementException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WithdrawalMovements implements MovementProcessTypes {

  private final GetAccountService getAccountService;
  private final GetMovementService getMovementService;

  @Override
  public MovementTypeEnum type() {
    return MovementTypeEnum.RETIRO;
  }
  @Override
  public Movement process(Movement movement) {
    isNegativeValue(movement.getValue());
    Account account = getAccountService.getById(movement.getAccountNumber().getNumber());
    Movement movementFinded = getMovementService.lastMovement(account.getNumber());
    BigDecimal calculatedValue;
    if (Objects.nonNull(movementFinded)){
      calculatedValue = movementFinded.getBalance().plus().add(movement.getValue());
    } else {
      calculatedValue = account.getInitialBalance().plus().add(movement.getValue());
    }
    canBalance(calculatedValue);
    movement.setDateMovement(LocalDateTime.now());
    movement.setBalance(calculatedValue);
    return movement;
  }

  private void canBalance(BigDecimal movementValue) {
    if (movementValue.signum() == -1) {
      throw new InvalidMovementException();
    }
  }

  private void isNegativeValue(BigDecimal value){
    if (value.signum() != -1){
      throw new InvalidValueMovementException();
    }
  }

}
