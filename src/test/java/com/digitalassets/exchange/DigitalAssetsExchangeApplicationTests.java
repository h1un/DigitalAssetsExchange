package com.digitalassets.exchange;

import com.digitalassets.exchange.api.Exchange;
import com.digitalassets.exchange.api.ReflectionMethod;
import com.digitalassets.exchange.api.dto.BalanceParameter;
import com.digitalassets.exchange.api.dto.OrderbookParameter;
import com.digitalassets.exchange.api.dto.TickerParameter;
import com.digitalassets.exchange.api.dto.TradeParameter;
import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.HashMap;

@SpringBootTest
class DigitalAssetsExchangeApplicationTests {
    @Autowired
    UpbitWebClient upbitWebClient;
    @Autowired
    ReflectionMethod reflectionMethod;
    String apiKey = "DNkgnGpmm4RuHxmnEZ1Ewer1xkxByMZFn0G7vKA6";
    String secretKey = "1k3kLy5qigPe4m15aQGYFFt6e5H8Wx8wEgt4h5ih";


    @Test
    void contextLoads() {
    }

    @Test
    void getPublic() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("markets", "KRW-BTC");
        hashMap.put("count", "1");
        System.out.println(upbitWebClient.getPublic("/v1/orderbook", hashMap).block());
    }

    @Test
    void getPrivate() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("market", "KRW-BTC");
        System.out.println(upbitWebClient.get("/v1/accounts", hashMap, apiKey, secretKey).block());

    }

    @Test
    void postPrivate() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("market", "KRW-BTC");
        hashMap.put("side", "bid");
        hashMap.put("volume", "0.001");
        hashMap.put("price", "70000000");
        hashMap.put("ord_type", "limit");
        System.out.println(upbitWebClient.post("/v1/orders", hashMap, apiKey, secretKey).block());

    }


    @Test
    void deletePrivate() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("uuid", "cdd92199-2897-4e14-9448-f923320408ad");

        System.out.println(upbitWebClient.delete("/v1/order", hashMap, apiKey, secretKey).block());

    }

    @SneakyThrows
    @Test
    void d() {
        try {
//            Class myClass = Class.forName("com.digitalassets.exchange.api.upbit.UpbitService");
//
//            Method method = myClass.getMethod("a", new Class[]{String.class});
//            System.out.println(method.invoke(myClass.newInstance(), "a"));

            OrderbookParameter orderbookParameter = OrderbookParameter.builder()
                    .currency("BTC")
                    .payment("KRW").build();

            String site = "upbit";

            Exchange service = Exchange.getService(site);

            Class myClass2 = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() +"." + service.getServiceName());

            Method method2 = myClass2.getMethod("getOrderbook", OrderbookParameter.class);
            HashMap<String, Object> hashMap = new HashMap();

            System.out.println(method2.invoke(myClass2.newInstance(), orderbookParameter));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void reflection() {

//        OrderbookParameter orderbookParameter = OrderbookParameter.builder()
//                .currency("BTC")
//                .payment("KRW").build();
//
//        System.out.println(reflectionMethod.getOrderbook(orderbookParameter,"upbit"));
//
//        TickerParameter tickerParameter = TickerParameter.builder()
//                .currency("BTC")
//                .payment("KRW").build();
//
//        System.out.println(reflectionMethod.getTicker(tickerParameter,"upbit"));
//
//        TradeParameter tradeParameter = TradeParameter.builder()
//                .currency("BTC")
//                .payment("KRW").build();
//
//        System.out.println(reflectionMethod.getTrade(tradeParameter,"upbit"));

        BalanceParameter balanceParameter = BalanceParameter.builder()
                .apiKey(apiKey).secretKey(secretKey).currency("ETH").payment("BTC").build();

        System.out.println(reflectionMethod.getBalance(balanceParameter,"upbit"));
    }


}
