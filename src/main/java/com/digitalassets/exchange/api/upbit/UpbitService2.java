package com.digitalassets.exchange.api.upbit;

import com.digitalassets.exchange.api.dto.OrderbookParameter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service

public class UpbitService2 {

    String ORDERBOOK_URI = "/v1/orderbook";


    private final UpbitWebClient upbitWebClient;

    public UpbitService2() {
        upbitWebClient = new UpbitWebClient();
    }

    public String getOrderbook(OrderbookParameter orderbookParameter) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("markets", orderbookParameter.getPayment() + "-" + orderbookParameter.getCurrency());
        return String.valueOf(upbitWebClient.getPublic(ORDERBOOK_URI, params).block());

    }
}
