package org.neoris.microservices.clients.infrastructure.output.repository.dto.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.neoris.microservices.clients.domain.model.Person;
import org.neoris.microservices.clients.infrastructure.output.repository.dto.person.PersonDTO;
import org.neoris.microservices.clients.shared.EntityMapper;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PersonDtoMapper extends EntityMapper<PersonDTO, Person> {

}
