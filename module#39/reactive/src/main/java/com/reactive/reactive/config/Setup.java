package com.reactive.reactive.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.reactive.model.Sport;
import com.reactive.reactive.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Setup implements CommandLineRunner {

    @Autowired
    private SportService sportService;

    @Autowired
    private WebClient webClient;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Data loading...");

        Mono<JsonNode> sportClient = this.getInitialData2();
        sportClient.flatMapIterable(x -> x.get("data"))
                .limitRate(20)
                .subscribe(node -> this.saveInitialData(node));

        System.out.println("Data initialized!");
    }

    private Mono<JsonNode> getInitialData2() {
        return this.webClient
                .get()
                .uri("https://sports.api.decathlon.com/sports")
                .retrieve()
                .bodyToMono(JsonNode.class);


    }

    private void saveInitialData(JsonNode data) {
        this.sportService.createSportByName(data.get("attributes").get("name").asText());
    }
}
