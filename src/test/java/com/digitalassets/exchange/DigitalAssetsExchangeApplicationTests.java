package com.digitalassets.exchange;

import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigitalAssetsExchangeApplicationTests {

    @Autowired
    UpbitWebClient upbitWebClient;

    @Test
    void contextLoads() {
    }

    @Test
    void getPublic() {
        System.out.println(upbitWebClient.getPublic("/v1/market/all2").block());
    }

}
