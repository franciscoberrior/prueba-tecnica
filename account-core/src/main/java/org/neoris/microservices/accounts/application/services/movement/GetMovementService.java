package org.neoris.microservices.accounts.application.services.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.GetMovementServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.movement.MovementGetService;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetMovementService implements GetMovementServiceInputPort {

  private final MovementGetService movementGetService;

  @Override
  public Movement getById(Long id) {
    return movementGetService.getById(id);
  }

  @Override
  public Movement lastMovement(String accountNumber) {
    return movementGetService.lastMovement(accountNumber);
  }
}
