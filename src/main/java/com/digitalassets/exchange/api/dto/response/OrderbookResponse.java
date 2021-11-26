package com.digitalassets.exchange.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Builder
@Getter
@ToString
public class OrderbookResponse {

    String status;
    String message;
    String currency;
    String payment;
    List<OrderbookData> asks;
    List<OrderbookData> bids;

    @ToString
    @Getter
    @Builder
    public static class OrderbookData {
        String price;
        String units;
    }
}
