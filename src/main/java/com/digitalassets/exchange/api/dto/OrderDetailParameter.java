package com.digitalassets.exchange.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderDetailParameter {
    String orderId;
    String apiKey;
    String secretKey;
}
