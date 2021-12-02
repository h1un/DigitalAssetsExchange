package com.digitalassets.exchange.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
public class WaitListResponse {

    String status;
    String message;
    List<WaitListData> waitListData;

    @Getter
    @Builder
    @ToString
    public static class WaitListData {
        String orderId;
        String price;
        String volume;
        String remainingVolume;
        LocalDateTime timestamp;
        String type;
        String currency;
        String payment;
    }
}
