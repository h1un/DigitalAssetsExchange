package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpbitTrade {

    String trade_time_utc;
    String trade_date_utc;
    String timestamp;
    String trade_price;
    String trade_volume;
    String prev_closing_price;
    String change_price;
    String ask_bid;
    String sequential_id;

}


