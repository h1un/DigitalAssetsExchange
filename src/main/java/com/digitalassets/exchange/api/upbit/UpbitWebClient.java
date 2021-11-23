package com.digitalassets.exchange.api.upbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;
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

    public Mono<String> getPrivate(String uri, HashMap<String, Object> hashMap, String apiKey, String secretKey) {

        String queryString = hashMap.entrySet().stream().map(map -> map.getKey() + "=" + map.getValue()).collect(Collectors.joining("&"));

        String auth = auth(queryString, apiKey, secretKey);

        return webClient.get()
                .uri(uri + "?" + queryString)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", auth)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> Mono.empty())
                .bodyToMono(String.class);
    }

    public String auth(String queryString, String apiKey, String secretKey) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String jwtToken = JWT.create()
                .withClaim("access_key", apiKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        return authenticationToken;
    }

}
