package com.devsu.controller;

import org.openapitools.api.ClientesApi;
import org.openapitools.api.ClientesApiDelegate;
import org.openapitools.model.ClientResponse;
import org.openapitools.model.CreateClientRequest;
import org.openapitools.model.ReplaceClientRequest;
import org.openapitools.model.UpdateClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class ClientsController implements ClientesApi {

  @Override
  public Mono<ResponseEntity<ClientResponse>> createClient(Mono<CreateClientRequest> createClientRequest, ServerWebExchange exchange) {
    return ClientesApi.super.createClient(createClientRequest, exchange);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteClient(Integer id, ServerWebExchange exchange) {
    return ClientesApi.super.deleteClient(id, exchange);
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> getClient(Integer id, ServerWebExchange exchange) {
    return ClientesApi.super.getClient(id, exchange);
  }

  @Override
  public Mono<ResponseEntity<Flux<ClientResponse>>> listClients(ServerWebExchange exchange) {
    return ClientesApi.super.listClients(exchange);
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> replaceClient(Mono<ReplaceClientRequest> replaceClientRequest, ServerWebExchange exchange) {
    return ClientesApi.super.replaceClient(replaceClientRequest, exchange);
  }

  @Override
  public Mono<ResponseEntity<ClientResponse>> updateClient(Mono<UpdateClientRequest> updateClientRequest, ServerWebExchange exchange) {
    return ClientesApi.super.updateClient(updateClientRequest, exchange);
  }
}
