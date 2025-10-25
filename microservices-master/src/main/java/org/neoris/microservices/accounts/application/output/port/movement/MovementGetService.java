package org.neoris.microservices.accounts.application.output.port.movement;

import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementGetService {

  Movement getById(Long id);

  Movement lastMovement(String accountNumber);

}
