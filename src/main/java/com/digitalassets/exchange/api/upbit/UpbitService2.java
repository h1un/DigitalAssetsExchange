package com.digitalassets.exchange.api.upbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service

public class UpbitService2 {
    
    private final UpbitWebClient upbitWebClient;

    public UpbitService2() {
        upbitWebClient = new UpbitWebClient();
    }

    public String a(HashMap<String, Object> hashMap) {
        return String.valueOf(upbitWebClient.getPublic("/v1/market/all", hashMap).block());

    }
}
