package org.neoris.microservices.accounts.application.input.port.movement;

import org.neoris.microservices.accounts.domain.model.Movement;

public interface SaveMovementServiceInputPort {

  Movement save(Movement movement);

}
