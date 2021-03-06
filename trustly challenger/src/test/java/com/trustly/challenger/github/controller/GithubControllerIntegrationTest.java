package com.trustly.challenger.github.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.trustly.challenger.github.exception.NotFoundException;
import com.trustly.challenger.github.mapper.RepoRepresentationMapper;
import com.trustly.challenger.github.presentation.controller.GithubController;
import com.trustly.challenger.github.presentation.controller.representation.ExtensionRepresentation;
import com.trustly.challenger.github.service.GithubService;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = GithubController.class)
class GithubControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private GithubService service;

    @MockBean
    private RepoRepresentationMapper mapper;

    @Test
    void shouldReturn200() {

        String user = "JeanFabichaki";
        String repo = "todo-rust";

        Mockito.when(service.getRepoInformation(user, repo))
            .thenReturn(Flux.just(ExtensionRepresentation.builder()
                .totalLines(1l)
                .extension("java")
                .totalBytes(1L)
                .build()));

        webClient.get()
            .uri(uriBuilder -> uriBuilder
            .path("/api/user/{user}/repo/{repo}")
            .build(user, repo))
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isOk();

        Mockito.verify(service, Mockito.times(1)).getRepoInformation(user, repo);
    }

    @Test
    void shouldReturn404WithInvalidRepo(){

        String user = "JeanFabichaki";
        String repo = "notfound";

        Mockito.when(service.getRepoInformation(user, repo))
            .thenReturn(Flux.error(new NotFoundException()));

        webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/user/{user}/repo/{repo}")
                .build(user, repo))
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isNotFound();

        Mockito.verify(service, Mockito.times(1)).getRepoInformation(user, repo);

    }
}
