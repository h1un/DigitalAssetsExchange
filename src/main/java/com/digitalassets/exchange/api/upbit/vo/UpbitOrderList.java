package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UpbitOrderList {
    String uuid;
    String side;
    String ord_type;
    String price;
    String state;
    String market;
    String created_at;
    String volume;
    String remaining_volume;
    String reserved_fee;
    String remaining_fee;
    String paid_fee;
    String locked;
    String executed_volume;
    String trade_count;
}
