package org.neoris.microservices.accounts.application.services.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.DeleteMovementServiceInputPort;
import org.neoris.microservices.accounts.application.output.port.movement.MovementDeleteService;
import org.neoris.microservices.accounts.application.output.port.movement.MovementGetService;
import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteMovementService implements DeleteMovementServiceInputPort {

  private final MovementDeleteService movementDeleteService;
  private final MovementGetService movementGetService;
  private final MovementsToAccountService movementsToAccountService;

  @Override
  public void delete(Long id) {
    var movementToUpdate = movementGetService.getById(id);
    if (MovementTypeEnum.RETIRO.equals(movementToUpdate.getType())){
      movementToUpdate.setType(MovementTypeEnum.DEPOSITO);
      movementToUpdate.setValue(movementToUpdate.getValue().abs());
      movementsToAccountService.movement(movementToUpdate);
    } else {
      movementToUpdate.setType(MovementTypeEnum.RETIRO);
      movementToUpdate.setValue(movementToUpdate.getValue().negate());
      movementsToAccountService.movement(movementToUpdate);
    }
    movementDeleteService.deleteById(id);
  }

}
