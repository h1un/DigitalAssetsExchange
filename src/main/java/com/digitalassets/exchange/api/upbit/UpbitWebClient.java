package com.digitalassets.exchange.api.upbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class UpbitWebClient {

    String SERVER_URL = "https://api.upbit.com";
    WebClient webClient = WebClient.create(SERVER_URL);


    public Mono<String> getPublic(String uri) {


        return webClient.get()
                .uri(uri)
                .header("Content-Type", "application/json")
                .retrieve().bodyToMono(String.class);



    }

}
