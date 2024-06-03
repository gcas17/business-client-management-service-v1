package com.devsu.mappers;

import com.devsu.model.entities.Client;
import com.devsu.model.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.ClientResponse;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  @Mapping(source = "password", target = "password")
  @Mapping(source = "status", target = "status")
  @Mapping(target = "personId", ignore = true)
  Client createClientRequestToClient(CreateClientRequest createClientRequest);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "status", target = "status")
  @Mapping(target = "personId", ignore = true)
  void replaceClientRequestToClient(ReplaceClientRequest replaceClientRequest, @MappingTarget Client client);

  @Mapping(source = "client.id", target = "id")
  @Mapping(source = "client.password", target = "password")
  @Mapping(source = "client.status", target = "status")
  @Mapping(source = "person.name", target = "name")
  @Mapping(source = "person.address", target = "address")
  @Mapping(source = "person.phone", target = "phone")
  ClientResponse clientToClientResponse(Client client, Person person);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "status", target = "status")
  @Mapping(target = "personId", ignore = true)
  void updateClientRequestToClient(UpdateClientRequest updateClientRequest, @MappingTarget Client client);

}
