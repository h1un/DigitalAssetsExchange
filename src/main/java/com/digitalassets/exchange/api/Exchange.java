package com.digitalassets.exchange.api;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;

@Getter
public enum Exchange {
    UPBIT("upbit", "UpbitService", "UpbitWebClient");

    private final String exchangeName;
    private final String serviceName;
    private final String webClientName;

    Exchange(String exchangeName, String serviceName, String webClientName) {
        this.exchangeName = exchangeName;
        this.serviceName = serviceName;
        this.webClientName = webClientName;
    }

    @SneakyThrows
    public static Exchange getService(String exchangeName) {
        return Arrays.stream(Exchange.values()).filter(exchange -> exchange.exchangeName.equals(exchangeName))
                .findAny()
                .orElseThrow(() -> new IllegalAccessException("존재하지 않는 거래소"))
                ;
    }
}
