package com.digitalassets.exchange.api;

import com.digitalassets.exchange.api.dto.*;
import com.digitalassets.exchange.api.upbit.UpbitWebClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Component
public class ReflectionMethod {

    @SneakyThrows
    public String getOrderbook(OrderbookParameter orderbookParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());

        Method method = exchangeService.getMethod("getOrderbook", OrderbookParameter.class);
        return method.invoke(exchangeService.newInstance(), orderbookParameter).toString();
    }

    @SneakyThrows
    public String getTicker(TickerParameter tickerParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("getTicker", TickerParameter.class);

        return method.invoke(o, tickerParameter).toString();
    }

    @SneakyThrows
    public String getTrade(TradeParameter tradeParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("getTrade", TradeParameter.class);

        return method.invoke(o, tradeParameter).toString();
    }

    @SneakyThrows
    public String getBalance(BalanceParameter balanceParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("getBalance", BalanceParameter.class);

        return method.invoke(o, balanceParameter).toString();
    }

    @SneakyThrows
    public String orderLimitBid(OrderParameter orderParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("orderLimitBid", OrderParameter.class);

        return method.invoke(o, orderParameter).toString();
    }

    @SneakyThrows
    public String orderLimitAsk(OrderParameter orderParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("orderLimitAsk", OrderParameter.class);

        return method.invoke(o, orderParameter).toString();
    }

    @SneakyThrows
    public String getOrderDetail(OrderDetailParameter orderDetailParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("orderLimitAsk", OrderDetailParameter.class);

        return method.invoke(o, orderDetailParameter).toString();
    }

    @SneakyThrows
    public String getOrderCancel(OrderCancelParameter orderDetailParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("getOrderCancel", OrderCancelParameter.class);

        return method.invoke(o, orderDetailParameter).toString();
    }

    @SneakyThrows
    public String getWaitList(WaitListParameter waitListParameter, String site) {

        Exchange service = Exchange.getService(site);
        Class<?> exchangeService = Class.forName("com.digitalassets.exchange.api." + service.getExchangeName() + "." + service.getServiceName());
        Constructor<?> constructor = exchangeService.getConstructor();
        Object o = constructor.newInstance();
        Method method = exchangeService.getMethod("getWaitList", WaitListParameter.class);

        return method.invoke(o, waitListParameter).toString();
    }


}
