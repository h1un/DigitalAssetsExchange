package com.digitalassets.exchange.api.upbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.stream.Collectors;

@Configuration
public class UpbitWebClient {

    String SERVER_URL = "https://api.upbit.com";
    WebClient webClient = WebClient.create(SERVER_URL);


    public Mono<String> getPublic(String uri, HashMap<String, Object> hashMap) {

        String queryString = hashMap.entrySet().stream().map(map -> map.getKey() + "=" + map.getValue()).collect(Collectors.joining("&"));

        return webClient.get()
                .uri(uri + "?" + queryString)
//                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
//                        clientResponse -> Mono.error(new RuntimeException())) // Exception 처리
//                        clientResponse -> clientResponse.bodyToMono(String.class).map(body -> new RuntimeException(body))) //Exception 처리 , Error 내용 출력
                        clientResponse -> Mono.empty()) // 에러 내지 않고 그대로 받기
                .bodyToMono(String.class);


    }

}
