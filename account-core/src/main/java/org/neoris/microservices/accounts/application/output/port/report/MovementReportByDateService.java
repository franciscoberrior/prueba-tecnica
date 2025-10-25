package org.neoris.microservices.accounts.application.output.port.report;

import java.time.LocalDateTime;
import java.util.List;
import org.neoris.microservices.accounts.domain.model.Movement;

public interface MovementReportByDateService {

  List<Movement> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

}
