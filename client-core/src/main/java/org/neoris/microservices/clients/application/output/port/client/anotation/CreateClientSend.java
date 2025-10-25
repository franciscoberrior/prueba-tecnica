package org.neoris.microservices.clients.application.output.port.client.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.neoris.microservices.clients.domain.enums.ProcessClientTypeEnum;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateClientSend {

  ProcessClientTypeEnum type();
}