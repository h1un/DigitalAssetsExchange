package com.digitalassets.exchange.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class Parameter {

    @Builder
    @Getter
    @Setter
    public static class Balance {
        String currency;
        String payment;
        String apiKey;
        String secretKey;
    }

    @Builder
    @Getter
    @Setter
    public static class Orderbook {
        String currency;
        String payment;
    }

    @Builder
    @Getter
    @Setter
    public static class OrderCancel {
        String orderId;
        String apiKey;
        String secretKey;
    }

    @Builder
    @Getter
    @Setter
    public static class OrderDetail {
        String orderId;
        String apiKey;
        String secretKey;
    }

    @Builder
    @Getter
    @Setter
    public static class Order {
        String currency;
        String payment;
        String apiKey;
        String secretKey;
        String price;
        String volume;
    }

    @Builder
    @Getter
    @Setter
    public static class Ticker {
        String currency;
        String payment;
    }

    @Builder
    @Getter
    @Setter
    public static class Trade {
        String currency;
        String payment;
    }

    @Builder
    @Getter
    @Setter
    public static class WaitList {
        String currency;
        String payment;
        String apiKey;
        String secretKey;
    }



}
