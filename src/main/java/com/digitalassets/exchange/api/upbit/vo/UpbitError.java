package com.digitalassets.exchange.api.upbit.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpbitError {

    ErrorData error;

    @Getter
    @NoArgsConstructor
    public static class ErrorData {
        String name;
        String message;
    }
}
