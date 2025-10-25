package org.neoris.microservices.clients.application.output.port.person;

import org.neoris.microservices.clients.domain.model.Person;

public interface PersonUpdateService {

  void update(Person person);

}
