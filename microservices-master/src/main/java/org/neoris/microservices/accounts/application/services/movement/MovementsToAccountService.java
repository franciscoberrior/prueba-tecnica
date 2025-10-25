package org.neoris.microservices.accounts.application.services.movement;

import java.util.List;
import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.MovementsToAccountServiceInputPort;
import org.neoris.microservices.accounts.application.services.movement.types.MovementProcessTypes;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.shared.exception.domain.InvalidTypeMovementException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MovementsToAccountService implements MovementsToAccountServiceInputPort {

  private final List<MovementProcessTypes> movementProcessTypes;

  @Override
  public Movement movement(Movement movement) {
    return movementProcessTypes.stream()
        .filter(mpt -> mpt.type().equals(movement.getType()))
        .findFirst()
        .orElseThrow(InvalidTypeMovementException::new)
        .process(movement);
  }

}
