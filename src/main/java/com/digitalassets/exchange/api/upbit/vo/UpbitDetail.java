package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Getter
public class UpbitDetail {
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
    List<OrderDetailData> trades;

    @ToString
    @NoArgsConstructor
    @Getter
    public class OrderDetailData {
        String market;
        String uuid;
        String price;
        String volume;
        String funds;
        String ask;
    }
}
