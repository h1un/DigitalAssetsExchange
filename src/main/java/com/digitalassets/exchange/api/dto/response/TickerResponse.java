package com.digitalassets.exchange.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TickerResponse {

    String status;
    String message;
    String currency;
    String payment;
    String openingPrice;
    String highPrice;
    String lowPrice;
    String tradePrice;

}
