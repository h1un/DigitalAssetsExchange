package com.digitalassets.exchange.api;

import com.digitalassets.exchange.api.dto.OrderbookParameter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Component
public class ReflectionMethod {

    @SneakyThrows
    public String getOrderbook(OrderbookParameter orderbookParameter, String site) {

        Exchange service = Exchange.getService(site);

        Class ExchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() +"." + service.getServiceName());

        Method method = ExchangeService.getMethod("getOrderbook", OrderbookParameter.class);
        HashMap<String, Object> hashMap = new HashMap();

        return method.invoke(ExchangeService.newInstance(), orderbookParameter).toString();

    }

}
