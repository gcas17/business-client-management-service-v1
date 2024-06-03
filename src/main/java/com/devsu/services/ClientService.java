package com.devsu.services;

import org.openapitools.model.ClientResponse;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

  Mono<ClientResponse> createClient(Mono<CreateClientRequest> createClientRequest);

  Mono<Void> deleteClient(Long id);

  Mono<ClientResponse> getClient(Long id);

  Mono<ClientResponse> replaceClient(Mono<ReplaceClientRequest> replaceClientRequest);

  Mono<ClientResponse> updateClient(Mono<UpdateClientRequest> updateClientRequest);

  Flux<ClientResponse> listClients();

}
