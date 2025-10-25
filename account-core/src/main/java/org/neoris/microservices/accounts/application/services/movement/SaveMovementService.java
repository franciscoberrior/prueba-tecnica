package org.neoris.microservices.accounts.application.services.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.SaveMovementServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.movement.MovementSaveService;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaveMovementService implements SaveMovementServiceInputPort {

  private final MovementSaveService movementSaveService;
  private final MovementsToAccountService movementsToAccountService;

  @Override
  public Movement save(Movement movement) {
    movement = movementsToAccountService.movement(movement);
    return movementSaveService.save(movement);
  }

}
