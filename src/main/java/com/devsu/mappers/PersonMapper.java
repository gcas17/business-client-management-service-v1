package com.devsu.mappers;

import com.devsu.model.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonMapper {

  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "identification", target = "identification")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "phone", target = "phone")
  @Mapping(target = "id", ignore = true)
  Person createClientRequestToPerson(CreateClientRequest createClientRequest);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "identification", target = "identification")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "phone", target = "phone")
  @Mapping(target = "id", ignore = true)
  void replaceClientRequestToPerson(ReplaceClientRequest replaceClientRequest, @MappingTarget Person person);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "identification", target = "identification")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "phone", target = "phone")
  @Mapping(target = "id", ignore = true)
  Person updateClientRequestToPerson(UpdateClientRequest updateClientRequest, @MappingTarget Person person);

}
