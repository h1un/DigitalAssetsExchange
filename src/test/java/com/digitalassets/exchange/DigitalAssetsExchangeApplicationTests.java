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

    @Test
    void postPrivate() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("market", "KRW-BTC");
        hashMap.put("side", "bid");
        hashMap.put("volume", "0.001");
        hashMap.put("price", "70000000");
        hashMap.put("ord_type", "limit");
        System.out.println(upbitWebClient.postPrivate("/v1/orders",hashMap,apiKey,secretKey).block());

    }


    @Test
    void deletePrivate() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("uuid", "cdd92199-2897-4e14-9448-f923320408ad");

        System.out.println(upbitWebClient.deletePrivate("/v1/order",hashMap,apiKey,secretKey).block());

    }


}
