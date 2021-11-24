package com.digitalassets.exchange.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BalanceParameter {
    String currency;
    String payment;
    String apiKey;
    String secretKey;
}
