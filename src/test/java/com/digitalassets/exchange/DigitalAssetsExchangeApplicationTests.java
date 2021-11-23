package com.digitalassets.exchange;

import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class DigitalAssetsExchangeApplicationTests {

    String apiKey = "";
    String secretKey = "";

    @Autowired
    UpbitWebClient upbitWebClient;

    @Test
    void contextLoads() {
    }

    @Test
    void getPublic() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("market" , "KRW-BTC");
        hashMap.put("count" , "1");
        System.out.println(upbitWebClient.getPublic("/v1/trades/ticks",hashMap).block());
    }

    @Test
    void getPrivate() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("market" , "KRW-BTC");
        System.out.println(upbitWebClient.getPrivate("/v1/accounts",hashMap,apiKey,secretKey).block());

    }


}
