package org.neoris.microservices.accounts.infrastructure.output.adapter.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.output.port.movement.MovementDeleteService;
import org.neoris.microservices.accounts.application.output.port.movement.MovementGetService;
import org.neoris.microservices.accounts.application.output.port.movement.MovementSaveService;
import org.neoris.microservices.accounts.application.output.port.movement.MovementUpdateService;
import org.neoris.microservices.accounts.domain.model.Movement;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.MovementRepository;
import org.neoris.microservices.accounts.infrastructure.output.repository.dto.movement.mapper.MovementDtoMapper;
import org.neoris.microservices.accounts.shared.exception.domain.MovementNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MovementAdapter implements MovementSaveService, MovementGetService,
    MovementUpdateService, MovementDeleteService {

  private final MovementRepository movementRepository;
  private final MovementDtoMapper movementDtoMapper;

  @Override
  public void deleteById(Long id) {
    movementRepository.deleteById(id);
  }

  @Override
  public Movement getById(Long id) {
    return movementDtoMapper.toEntity(
        movementRepository.findById(id).orElseThrow(MovementNotFoundException::new));
  }

  @Override
  public Movement lastMovement(String accountNumber) {
    return movementDtoMapper.toEntity(
        movementRepository.findLastMovement(accountNumber).orElse(null));
  }

  @Override
  public Movement save(Movement movement) {
    return movementDtoMapper.toEntity(movementRepository.save(movementDtoMapper.toDto(movement)));
  }

  @Override
  public void update(Movement movement) {
    movementRepository.findById(movement.getId())
        .ifPresent(mv -> movementRepository.save(movementDtoMapper.toDto(movement)));
  }
}
