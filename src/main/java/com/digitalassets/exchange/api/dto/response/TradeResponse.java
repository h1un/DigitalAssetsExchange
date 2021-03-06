package com.digitalassets.exchange.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
public class TradeResponse {

    String status;
    String message;
    String currency;
    String payment;
    List<TradeData> trade;

    @ToString
    @Getter
    @Builder
    public static class TradeData {
        String price;
        String volume;
        LocalDateTime timestamp;
        String type;
    }

}
