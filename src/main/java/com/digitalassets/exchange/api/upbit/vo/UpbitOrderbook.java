package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class UpbitOrderbook {
    String market;
    String timestamp;
    String total_ask_size;
    String total_bid_size;
    List<OrderbookUnits> orderbook_units;

    @Getter
    @NoArgsConstructor
    public static class OrderbookUnits {
        String ask_price;
        String bid_price;
        String ask_size;
        String bid_size;

    }
}
