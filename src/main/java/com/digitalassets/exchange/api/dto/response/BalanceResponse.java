package com.digitalassets.exchange.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BalanceResponse {
    String status;
    String message;
    BalanceData askBalance;
    BalanceData bidBalance;


    @Builder
    @Getter
    @ToString
    public static class BalanceData {
        String currency;
        String balance;
        String locked;
        String total;
        String avgBuyPrice;
    }
}
