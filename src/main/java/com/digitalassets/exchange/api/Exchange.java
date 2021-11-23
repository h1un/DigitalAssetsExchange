package com.digitalassets.exchange.api;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;
@Getter
public enum Exchange {
    UPBIT("upbit", "UpbitService");

    private final String exchangeName;
    private final String serviceName;

    Exchange(String exchangeName, String serviceName) {
        this.exchangeName = exchangeName;
        this.serviceName = serviceName;
    }

    @SneakyThrows
    public static Exchange getService(String exchangeName) {
        return Arrays.stream(Exchange.values()).filter(exchange -> exchange.exchangeName.equals(exchangeName))
                .findAny()
                .orElseThrow(() -> new IllegalAccessException("존재하지 않는 거래소"))
                ;
    }
}
