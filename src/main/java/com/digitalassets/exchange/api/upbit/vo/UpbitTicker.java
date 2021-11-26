package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
public class UpbitTicker {

    String market;
    String trade_date;
    String trade_time;
    String trade_date_kst;
    String trade_time_kst;
    String trade_timestamp;
    String opening_price;
    String high_price;
    String low_price;
    String trade_price;
    String prev_closing_price;
    String change;
    String change_price;
    String change_rate;
    String signed_change_price;
    String signed_change_rate;
    String trade_volume;
    String acc_trade_price;
    String acc_trade_price_24h;
    String acc_trade_volume;
    String acc_trade_volume_24h;
    String highest_52_week_price;
    String highest_52_week_date;
    String lowest_52_week_price;
    String lowest_52_week_date;
    String timestamp;

}


