package com.digitalassets.exchange.api.upbit;

import com.digitalassets.exchange.api.dto.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UpbitService {
    private static final String ORDERBOOK_URI = "/v1/orderbook";
    private static final String TICKER_URI = "/v1/ticker";
    private static final String TRADE_URI = "/v1/trades";
    private static final String BALANCE_URI = "/v1/orders/chance";
    private static final String ORDER_URI = "/v1/order";
    private static final String ORDERS_URI = "/v1/orders";

    private static final String ASK_STR = "ask";
    private static final String BID_STR = "bid";
    private static final String LIMIT_STR = "limit";
    private static final String MARKET_BID_STR = "price";
    private static final String MARKET_ASK_STR = "market";
    private static final String WAIT_STR = "wait";
    private static final String HYPHEN_STR = "-";

    private final UpbitWebClient upbitWebClient;

    public UpbitService() {
        upbitWebClient = new UpbitWebClient();
    }

    public String getOrderbook(OrderbookParameter orderbookParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("markets", orderbookParameter.getPayment() + HYPHEN_STR + orderbookParameter.getCurrency());
        return String.valueOf(upbitWebClient.getPublic(ORDERBOOK_URI, params).block());
    }

    public String getTicker(TickerParameter tickerParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("markets", tickerParameter.getPayment() + HYPHEN_STR + tickerParameter.getCurrency());
        return String.valueOf(upbitWebClient.getPublic(TICKER_URI, params).block());
    }

    public String getTrade(TradeParameter tradeParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("market", tradeParameter.getPayment() + HYPHEN_STR + tradeParameter.getCurrency());
        return String.valueOf(upbitWebClient.getPublic(TRADE_URI, params).block());
    }

    public String getBalance(Parameter.Balance balanceParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("market", balanceParameter.getPayment() + HYPHEN_STR + balanceParameter.getCurrency());
        return String.valueOf(upbitWebClient.get(BALANCE_URI, params, balanceParameter.getApiKey(), balanceParameter.getSecretKey()).block());
    }

    public String orderLimitBid(OrderParameter orderParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("market", orderParameter.getPayment() + HYPHEN_STR + orderParameter.getCurrency());
        params.put("price", orderParameter.getPrice());
        params.put("volume", orderParameter.getVolume());
        params.put("ord_type", LIMIT_STR);
        params.put("side", BID_STR);
        return String.valueOf(upbitWebClient.post(ORDER_URI, params, orderParameter.getApiKey(), orderParameter.getSecretKey()).block());
    }

    public String orderLimitAsk(OrderParameter orderParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("market", orderParameter.getPayment() + HYPHEN_STR + orderParameter.getCurrency());
        params.put("price", orderParameter.getPrice());
        params.put("volume", orderParameter.getVolume());
        params.put("ord_type", LIMIT_STR);
        params.put("side", ASK_STR);
        return String.valueOf(upbitWebClient.post(ORDER_URI, params, orderParameter.getApiKey(), orderParameter.getSecretKey()).block());
    }

    public String getOrderDetail(OrderDetailParameter orderDetailParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("uuid", orderDetailParameter.getOrderId());
        return String.valueOf(upbitWebClient.get(ORDER_URI, params, orderDetailParameter.getApiKey(), orderDetailParameter.getSecretKey()).block());
    }

    public String getOrderCancel(OrderCancelParameter orderDetailParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("uuid", orderDetailParameter.getOrderId());
        return String.valueOf(upbitWebClient.delete(ORDER_URI, params, orderDetailParameter.getApiKey(), orderDetailParameter.getSecretKey()).block());
    }

    public String getWaitList(WaitListParameter orderDetailParameter) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("market", orderDetailParameter.getPayment() + HYPHEN_STR + orderDetailParameter.getCurrency());
        params.put("state", WAIT_STR);
        return String.valueOf(upbitWebClient.get(ORDERS_URI, params, orderDetailParameter.getApiKey(), orderDetailParameter.getSecretKey()).block());
    }

}
