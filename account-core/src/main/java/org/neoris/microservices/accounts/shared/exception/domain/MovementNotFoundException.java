package org.neoris.microservices.accounts.shared.exception.domain;


import org.neoris.microservices.accounts.shared.common.MessagesEnum;
import org.neoris.microservices.accounts.shared.exception.base.BusinessException;
import org.neoris.microservices.accounts.shared.exception.base.ExceptionCode;
import org.springframework.http.HttpStatus;

public class MovementNotFoundException extends BusinessException {

  private static final long serialVersionUID = 2820617043783719815L;

  public MovementNotFoundException() {
    super(ExceptionCode.BUSINESS, MessagesEnum.MOVEMENT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, false);
  }

}