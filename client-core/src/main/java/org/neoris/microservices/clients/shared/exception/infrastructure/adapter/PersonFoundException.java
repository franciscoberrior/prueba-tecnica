package org.neoris.microservices.clients.shared.exception.infrastructure.adapter;


import org.neoris.microservices.clients.shared.common.MessagesEnum;
import org.neoris.microservices.clients.shared.exception.base.BusinessException;
import org.neoris.microservices.clients.shared.exception.base.ExceptionCode;
import org.springframework.http.HttpStatus;

public class PersonFoundException extends BusinessException {

  private static final long serialVersionUID = 2820617043783719815L;

  public PersonFoundException() {
    super(ExceptionCode.BUSINESS, MessagesEnum.PERSON_FOUND.getMessage(), HttpStatus.NOT_FOUND, false);
  }

}