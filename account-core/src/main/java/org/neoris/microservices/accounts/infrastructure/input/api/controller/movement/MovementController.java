package org.neoris.microservices.accounts.infrastructure.input.api.controller.movement;

import lombok.AllArgsConstructor;
import org.neoris.microservices.accounts.application.input.port.movement.DeleteMovementServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.movement.GetMovementServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.movement.SaveMovementServiceInputPort;
import org.neoris.microservices.accounts.application.input.port.movement.UpdateMovementServiceInputPort;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.MovementRequestDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.mapper.MovementResponseDtoMapper;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.request.MovementRequestDTO;
import org.neoris.microservices.accounts.infrastructure.input.api.dto.response.MovementResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovementController {

  private final SaveMovementServiceInputPort saveMovementService;
  private final GetMovementServiceInputPort getMovementService;
  private final UpdateMovementServiceInputPort updateMovementService;
  private final DeleteMovementServiceInputPort deleteMovementService;
  private final MovementRequestDtoMapper movementRequestDtoMapper;
  private final MovementResponseDtoMapper movementResponseDtoMapper;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody MovementRequestDTO movementRequest) {
    saveMovementService.save(movementRequestDtoMapper.toEntity(movementRequest));
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody MovementRequestDTO movementRequest) {
    updateMovementService.update(movementRequestDtoMapper.toEntity(movementRequest));
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<MovementResponseDTO> searchById(@RequestParam("movementId") Long movementId) {
    return ResponseEntity.ok(movementResponseDtoMapper.toDto(getMovementService.getById(movementId)));
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteById(@RequestParam("movementId") Long movementId) {
    deleteMovementService.delete(movementId);
    return ResponseEntity.ok().build();
  }
}
