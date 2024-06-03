package com.devsu.services.impl;

import com.devsu.mappers.ClientMapper;
import com.devsu.mappers.PersonMapper;
import com.devsu.model.entities.Client;
import com.devsu.model.entities.Person;
import com.devsu.repositories.ClientRepository;
import com.devsu.repositories.PersonRepository;
import com.devsu.services.ClientService;
import lombok.AllArgsConstructor;
import org.openapitools.model.ClientResponse;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static com.devsu.utilities.enums.ApiException.*;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final PersonRepository personRepository;

  @Override
  public Mono<ClientResponse> createClient(Mono<CreateClientRequest> createClientRequestMono) {
      return createClientRequestMono
          .flatMap(createClientRequest -> {
            if (!validateCreateClientRequest().test(createClientRequest)) {
              return Mono.error(CLI0006.getException());
            }
            Person person = PersonMapper.INSTANCE.createClientRequestToPerson(createClientRequest);
            Client client = ClientMapper.INSTANCE.createClientRequestToClient(createClientRequest);
            return personRepository.save(person)
                .flatMap(savedPerson -> {
                  client.setPersonId(savedPerson.getId());
                  return clientRepository.save(client);
                })
                .flatMap(savedClient -> Mono.just(ClientMapper.INSTANCE.clientToClientResponse(savedClient, person)));
          });
  }

  @Override
  public Mono<ClientResponse> getClient(Long id) {
    return clientRepository.findById(id)
        .flatMap(client -> personRepository.findById(Long.valueOf(client.getPersonId()))
            .map(person -> ClientMapper.INSTANCE.clientToClientResponse(client, person)));
  }

  @Override
  public Mono<ClientResponse> replaceClient(Mono<ReplaceClientRequest> replaceClientRequestMono) {
    return replaceClientRequestMono
        .flatMap(replaceClientRequest -> {
          if (!validateReplaceClientRequest().test(replaceClientRequest)) {
            return Mono.error(CLI0005.getException());
          }
          return clientRepository.findById(replaceClientRequest.getId())
              .switchIfEmpty(Mono.error(CLI0001.getException()))
              .map(existingClient -> {
                ClientMapper.INSTANCE.replaceClientRequestToClient(replaceClientRequest, existingClient);
                return existingClient;
              })
              .flatMap(clientRepository::save)
              .flatMap(client ->
                  personRepository.findById(Long.valueOf(client.getPersonId()))
                      .doOnNext(existingPerson -> PersonMapper.INSTANCE.replaceClientRequestToPerson(replaceClientRequest, existingPerson))
                      .flatMap(personRepository::save)
                      .map(person -> ClientMapper.INSTANCE.clientToClientResponse(client, person))
              );
        });
  }

  @Override
  public Mono<ClientResponse> updateClient(Mono<UpdateClientRequest> updateClientRequestMono) {
    return updateClientRequestMono
        .flatMap(updateClientRequest -> {
          if (!validateUpdateClientRequest().test(updateClientRequest)) {
            return Mono.error(CLI0005.getException());
          }
          return clientRepository.findById(updateClientRequest.getId())
              .switchIfEmpty(Mono.error(CLI0001.getException()))
              .map(existingClient -> {
                ClientMapper.INSTANCE.updateClientRequestToClient(updateClientRequest, existingClient);
                return existingClient;
              })
              .flatMap(clientRepository::save)
              .flatMap(client ->
                  personRepository.findById(Long.valueOf(client.getPersonId()))
                      .doOnNext(existingPerson -> PersonMapper.INSTANCE.updateClientRequestToPerson(updateClientRequest, existingPerson))
                      .flatMap(personRepository::save)
                      .map(person -> ClientMapper.INSTANCE.clientToClientResponse(client, person))
              );
        });
  }

  @Override
  public Flux<ClientResponse> listClients() {
    return clientRepository
        .findAll()
        .flatMap(client -> personRepository.findById(Long.valueOf(client.getPersonId()))
            .map(person -> ClientMapper.INSTANCE.clientToClientResponse(client, person)));
  }

  @Override
  public Mono<Void> deleteClient(Long id) {
    return clientRepository.findById(id)
        .switchIfEmpty(Mono.defer(() -> Mono.error(CLI0001.getException())))
        .flatMap(client -> personRepository.findById(Long.valueOf(client.getPersonId()))
            .flatMap(person -> personRepository.delete(person)
                .then(clientRepository.delete(client))));
  }

  private Predicate<UpdateClientRequest> validateUpdateClientRequest() {
    return updateClientRequest -> updateClientRequest.getId() != null;
  }

  private Predicate<ReplaceClientRequest> validateReplaceClientRequest() {
    return replaceClientRequest -> replaceClientRequest.getId() != null &&
        replaceClientRequest.getName() != null &&
        replaceClientRequest.getGender() != null &&
        replaceClientRequest.getAge() != null &&
        replaceClientRequest.getIdentification() != null &&
        replaceClientRequest.getAddress() != null &&
        replaceClientRequest.getPhone() != null &&
        replaceClientRequest.getPassword() != null &&
        replaceClientRequest.getStatus() != null;
  }

  private Predicate<CreateClientRequest> validateCreateClientRequest() {
    return createClientRequest -> createClientRequest.getName() != null &&
        createClientRequest.getGender() != null &&
        createClientRequest.getAge() != null &&
        createClientRequest.getIdentification() != null &&
        createClientRequest.getAddress() != null &&
        createClientRequest.getPhone() != null &&
        createClientRequest.getPassword() != null &&
        createClientRequest.getStatus() != null;
  }

}
