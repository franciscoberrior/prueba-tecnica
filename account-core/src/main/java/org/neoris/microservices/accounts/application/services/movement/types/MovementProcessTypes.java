package org.neoris.microservices.accounts.application.services.movement.types;

import org.neoris.microservices.accounts.domain.enums.MovementTypeEnum;
import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementProcessTypes {

  MovementTypeEnum type();

  Movement process(Movement movement);

}
