package com.devsu.controllers;

import com.devsu.services.ClientService;
import lombok.AllArgsConstructor;
import org.openapitools.api.ClientApi;
import org.openapitools.model.ClientResponse;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ClientController implements ClientApi {

  private final ClientService clientService;

  @Override
  public Mono<ResponseEntity<ClientResponse>> createClient(Mono<CreateClientRequest> createClientRequest, ServerWebExchange exchange) {
    return clientService.createClient(createClientRequest)
        .map(clientResponse -> ResponseEntity.ok().body(clientResponse))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> getClient(Long id, ServerWebExchange exchange) {
    return clientService.getClient(id)
        .map(clientResponse -> ResponseEntity.ok().body(clientResponse))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<ClientResponse>>> listClients(ServerWebExchange exchange) {
    return clientService
        .listClients().
        collectList()
        .map(clientList -> ResponseEntity.ok().body(Flux.fromIterable(clientList)))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> replaceClient(Mono<ReplaceClientRequest> replaceClientRequest, ServerWebExchange exchange) {
    return clientService.replaceClient(replaceClientRequest)
        .map(clientResponse -> ResponseEntity.ok().body(clientResponse))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> updateClient(Mono<UpdateClientRequest> updateClientRequest, ServerWebExchange exchange) {
    return clientService.updateClient(updateClientRequest)
        .map(clientResponse -> ResponseEntity.ok().body(clientResponse))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteClient(Long id, ServerWebExchange exchange) {
    return clientService.deleteClient(id)
        .then(Mono.just(new ResponseEntity<>(HttpStatus.OK)));
  }

}
