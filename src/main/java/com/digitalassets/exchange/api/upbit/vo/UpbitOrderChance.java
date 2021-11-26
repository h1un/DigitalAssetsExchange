package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UpbitOrderChance {

    String bid_fee;
    String ask_fee;
    String maker_bid_fee;
    String maker_ask_fee;
    Market market;
    Account bid_account;
    Account ask_account;

    @ToString
    @NoArgsConstructor
    @Getter
    public static class Account {
        String currency;
        String balance;
        String locked;
        String avg_buy_price;

    }

    @ToString
    @NoArgsConstructor
    @Getter
    public static class Market {
        String id;
        String name;
    }
}


