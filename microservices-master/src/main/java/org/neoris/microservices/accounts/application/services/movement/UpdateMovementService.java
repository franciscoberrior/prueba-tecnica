package org.neoris.microservices.accounts.application.services.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.UpdateMovementServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.movement.MovementGetService;
import org.neoris.microservices.accounts.application.output.port.movement.MovementUpdateService;
import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateMovementService implements UpdateMovementServiceInputPort {

  private final MovementUpdateService movementUpdateService;
  private final MovementGetService movementGetService;
  private final MovementsToAccountService movementsToAccountService;

  @Override
  public void update(Movement movement) {
    var movementToUpdate = movementGetService.getById(movement.getId());
    if (MovementTypeEnum.RETIRO.equals(movementToUpdate.getType())){
      movementToUpdate.setType(MovementTypeEnum.DEPOSITO);
      movementToUpdate.setValue(movementToUpdate.getValue().abs());
      movementsToAccountService.movement(movementToUpdate);
    } else {
      movementToUpdate.setType(MovementTypeEnum.RETIRO);
      movementToUpdate.setValue(movementToUpdate.getValue().negate());
      movementsToAccountService.movement(movementToUpdate);
    }
    movement = movementsToAccountService.movement(movement);
    movementUpdateService.update(movement);
  }

}
