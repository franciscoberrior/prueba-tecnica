package org.neoris.microservices.accounts.application.input.port.report;

import java.util.List;
import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementReportByDateInputPort {

  List<Movement> findBetweenDates(String dates);

}
