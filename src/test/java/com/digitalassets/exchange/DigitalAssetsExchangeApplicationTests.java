package com.digitalassets.exchange;

import com.digitalassets.exchange.api.Exchange;
import com.digitalassets.exchange.api.ReflectionMethod;
import com.digitalassets.exchange.api.dto.BalanceParameter;
import com.digitalassets.exchange.api.dto.OrderbookParameter;
import com.digitalassets.exchange.api.dto.TickerParameter;
import com.digitalassets.exchange.api.upbit.UpbitMapperService;
import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@SpringBootTest
class DigitalAssetsExchangeApplicationTests {
    @Autowired
    UpbitWebClient upbitWebClient;
    @Autowired
    ReflectionMethod reflectionMethod;
    @Autowired
    UpbitMapperService upbitMapperService;

    String apiKey = "DNkgnGpmm4RuHxmnEZ1Ewer1xkxByMZFn0G7vKA6";
    String secretKey = "1k3kLy5qigPe4m15aQGYFFt6e5H8Wx8wEgt4h5ih";


    @Test
    void contextLoads() {
    }

    @Test
    void getPublic() {

        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("markets", "KRW-BTC");
//        hashMap.put("count", "1");

//        Ticker ticker = upbitWebClient.geterror("/v1/ticker", hashMap).block();
        String ticker = upbitWebClient.getPublic("/v1/ticker", hashMap).block();
        System.out.println(ticker);

    }


//    @Test
//    void getModule() {
//
//        TickerParameter tickerParameter = TickerParameter.builder()
//                .currency("BTC")
//                .payment("KRW")
//                .build();
//
//        UpbitMapperService upbitMapperService = new UpbitMapperService(new UpbitService());
//
//        TickerResponse ticker = upbitMapperService.getTicker(tickerParameter);
//        log.info("{}",ticker);
//
//    }

//    @Test
//    void getModuleOrderbook() {
//
//        OrderbookParameter tickerParameter = OrderbookParameter.builder()
//                .currency("BT")
//                .payment("KRW")
//                .build();
//
//        UpbitMapperService upbitMapperService = new UpbitMapperService(new UpbitService());
//
//        OrderbookResponse ticker = upbitMapperService.getOrderbook(tickerParameter);
//        System.out.println(ticker);
//    }

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
        System.out.println(upbitWebClient.get("/v1/orders/chance", hashMap, apiKey, secretKey).block());

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

            Class myClass2 = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());

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

        OrderbookParameter balanceParameter = OrderbookParameter.builder()
                .currency("ETH").payment("BTC").build();

        System.out.println(reflectionMethod.getOrderbook(balanceParameter, "upbit"));


        System.out.println(reflectionMethod.getOrderbook(balanceParameter, "upbit"));

        System.out.println(reflectionMethod.getOrderbook(balanceParameter, "upbit"));
    }

    @Test
    void test0() {
        TickerParameter balanceParameter = TickerParameter.builder()
                .currency("ETH").payment("BTC").build();

        System.out.println(upbitMapperService.getTicker(balanceParameter));
    }

    @Test
    void test() {
    BalanceParameter balanceParameter = BalanceParameter.builder()
                .currency("ETH").payment("BTC").secretKey(secretKey).apiKey(apiKey).build();

    upbitMapperService.getBalance(balanceParameter);
    }

}
