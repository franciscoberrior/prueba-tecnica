package org.neoris.microservices.accounts.infrastructure.output.adapter.report;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.output.port.report.MovementReportByDateService;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.MovementRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.mapper.MovementDtoMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MovementsReportsAdapter implements MovementReportByDateService {

  private final MovementRepository movementRepository;
  private final MovementDtoMapper movementDtoMapper;

  @Override
  public List<Movement> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
    return movementDtoMapper.toEntity(movementRepository.findBetweenDates(startDate, endDate));
  }
}
