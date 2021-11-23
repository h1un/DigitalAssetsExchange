package com.digitalassets.exchange;

import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import com.sun.tools.javac.Main;
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
    String apiKey = "";
    String secretKey = "";


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
        System.out.println(upbitWebClient.get("/v1/accounts",hashMap,apiKey,secretKey).block());

    }

    @Test
    void postPrivate() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("market", "KRW-BTC");
        hashMap.put("side", "bid");
        hashMap.put("volume", "0.001");
        hashMap.put("price", "70000000");
        hashMap.put("ord_type", "limit");
        System.out.println(upbitWebClient.post("/v1/orders",hashMap,apiKey,secretKey).block());

    }


    @Test
    void deletePrivate() {

        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("uuid", "cdd92199-2897-4e14-9448-f923320408ad");

        System.out.println(upbitWebClient.delete("/v1/order",hashMap,apiKey,secretKey).block());

    }

    @SneakyThrows
    @Test
    void d(){
        try {
            Class myClass = Class.forName("com.digitalassets.exchange.api.upbit.UpbitService");

            Method method = myClass.getMethod("a", new Class[]{String.class});
            System.out.println(method.invoke(myClass.newInstance(), "a"));


            Class myClass2 = Class.forName("com.digitalassets.exchange.api.upbit.UpbitService2");

            Method method2 = myClass2.getMethod("a", HashMap.class);
            HashMap<String,Object> hashMap = new HashMap();

            System.out.println(method2.invoke(myClass2.newInstance(), new Object[]{hashMap}));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
