package com.digitalassets.exchange.api.upbit;

import com.digitalassets.exchange.api.dto.*;
import com.digitalassets.exchange.api.dto.response.*;
import com.digitalassets.exchange.api.upbit.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UpbitMapperService {

    private final UpbitService upbitService;

    private static final String SUCCESS = "0000";

    public UpbitMapperService(UpbitService upbitService) {
        this.upbitService = upbitService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    public TickerResponse getTicker(TickerParameter tickerParameter) {
        String result = upbitService.getTicker(tickerParameter);
        TickerResponse tickerResponse = null;
        try {
            UpbitTicker[] upbitTicker = objectMapper.readValue(result, UpbitTicker[].class);

            tickerResponse = TickerResponse.builder()
                    .status(SUCCESS)
                    .currency(tickerParameter.getCurrency())
                    .payment(tickerParameter.getPayment())
                    .highPrice(upbitTicker[0].getHigh_price())
                    .lowPrice(upbitTicker[0].getLow_price())
                    .openingPrice(upbitTicker[0].getOpening_price())
                    .tradePrice(upbitTicker[0].getTrade_price())
                    .build();

        } catch (JsonProcessingException e) {
            try {
                UpbitError upbitError = objectMapper.readValue(result, UpbitError.class);
                tickerResponse = TickerResponse.builder()
                        .status(upbitError.getError().getName())
                        .message(upbitError.getError().getMessage())
                        .currency(tickerParameter.getCurrency())
                        .payment(tickerParameter.getPayment())
                        .build();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        return tickerResponse;
    }

    public OrderbookResponse getOrderbook(OrderbookParameter orderbookParameter) {
        String result = upbitService.getOrderbook(orderbookParameter);

        OrderbookResponse orderbookResponse = null;
        try {
            UpbitOrderbook[] upbitOrderbook = objectMapper.readValue(result, UpbitOrderbook[].class);

            List<OrderbookResponse.OrderbookData> asks = new ArrayList<>();
            List<OrderbookResponse.OrderbookData> bids = new ArrayList<>();

            upbitOrderbook[0].getOrderbook_units()
                    .forEach(orderbookUnits -> {
                        OrderbookResponse.OrderbookData ask = OrderbookResponse.OrderbookData.builder()
                                .price(orderbookUnits.getAsk_price())
                                .volume(orderbookUnits.getAsk_size())
                                .build();
                        OrderbookResponse.OrderbookData bid = OrderbookResponse.OrderbookData.builder()
                                .price(orderbookUnits.getBid_price())
                                .volume(orderbookUnits.getBid_size()).build();
                        asks.add(ask);
                        bids.add(bid);
                    });

            orderbookResponse = OrderbookResponse.builder()
                    .status(SUCCESS)
                    .asks(asks)
                    .bids(bids)
                    .currency(orderbookParameter.getCurrency())
                    .payment(orderbookParameter.getPayment())
                    .build();
        } catch (JsonProcessingException e) {
            try {
                UpbitError upbitError = objectMapper.readValue(result, UpbitError.class);
                orderbookResponse = OrderbookResponse.builder()
                        .status(upbitError.getError().getName())
                        .message(upbitError.getError().getMessage())
                        .currency(orderbookParameter.getCurrency())
                        .payment(orderbookParameter.getPayment())
                        .build();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        return orderbookResponse;
    }


    public TradeResponse getTrade(TradeParameter tradeParameter) {
        String result = upbitService.getTrade(tradeParameter);

        TradeResponse tradeResponse = null;
        try {
            UpbitTrade[] upbitTrades = objectMapper.readValue(result, UpbitTrade[].class);


            List<TradeResponse.TradeData> trades = new ArrayList<>();

            Arrays.stream(upbitTrades).forEach(upbitTrade -> {
                TradeResponse.TradeData trade = TradeResponse.TradeData.builder()
                        .type(upbitTrade.getAsk_bid())
                        .price(upbitTrade.getTrade_price())
                        .volume(upbitTrade.getTrade_volume())
                        .timestamp(ZonedDateTime.parse(upbitTrade.getTimestamp()).toLocalDateTime())
                        .build();

                trades.add(trade);
            });

            tradeResponse = TradeResponse.builder()
                    .status(SUCCESS)
                    .currency(tradeParameter.getCurrency())
                    .payment(tradeParameter.getPayment())
                    .trade(trades)
                    .build();
        } catch (JsonProcessingException e) {
            try {
                UpbitError upbitError = objectMapper.readValue(result, UpbitError.class);
                tradeResponse = TradeResponse.builder()
                        .status(upbitError.getError().getName())
                        .message(upbitError.getError().getMessage())
                        .currency(tradeParameter.getCurrency())
                        .payment(tradeParameter.getPayment())
                        .build();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        return tradeResponse;
    }

    public BalanceResponse getBalance(BalanceParameter balanceParameter) {

        String result = upbitService.getBalance(balanceParameter);

        BalanceResponse balanceResponse = null;

        try {
            UpbitOrderChance upbitOrderChance = objectMapper.readValue(result, UpbitOrderChance.class);

            BalanceResponse.BalanceData askBalance = BalanceResponse.BalanceData.builder()
                    .currency(upbitOrderChance.getAsk_account().getCurrency())
                    .balance(upbitOrderChance.getAsk_account().getBalance())
                    .locked(upbitOrderChance.getAsk_account().getLocked())
                    .total("")
                    .build();
            BalanceResponse.BalanceData bidBalance = BalanceResponse.BalanceData.builder()
                    .currency(upbitOrderChance.getBid_account().getCurrency())
                    .balance(upbitOrderChance.getBid_account().getBalance())
                    .locked(upbitOrderChance.getBid_account().getLocked())
                    .total("")
                    .build();


            balanceResponse = BalanceResponse.builder()
                    .status(SUCCESS)
                    .askBalance(askBalance)
                    .bidBalance(bidBalance)
                    .build();

        } catch (JsonProcessingException e) {
            try {
                UpbitError upbitError = objectMapper.readValue(result, UpbitError.class);
                balanceResponse = BalanceResponse.builder()
                        .status(upbitError.getError().getName())
                        .message(upbitError.getError().getMessage())
                        .build();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }


        return balanceResponse;


    }

    public WaitListResponse getWaitList(WaitListParameter waitListParameter) {
        String result = upbitService.getWaitList(waitListParameter);
        WaitListResponse waitListResponse = null;
        try {
            UpbitOrderList[] upbitOrderLists = objectMapper.readValue(result, UpbitOrderList[].class);

            List<WaitListResponse.WaitListData> waitListList = new ArrayList<>();


            Arrays.stream(upbitOrderLists).forEach(upbitOrderList -> {
                WaitListResponse.WaitListData waitListData = WaitListResponse.WaitListData.builder()
                        .orderId(upbitOrderList.getUuid())
                        .price(upbitOrderList.getPrice())
                        .volume(upbitOrderList.getVolume())
                        .remainingVolume(upbitOrderList.getRemaining_volume())
                        .timestamp(ZonedDateTime.parse(upbitOrderList.getCreated_at()).toLocalDateTime())
                        .build();

                waitListList.add(waitListData);
            });

            waitListResponse = WaitListResponse.builder()
                    .status(SUCCESS)
                    .waitListData(waitListList)
                    .build();

        } catch (JsonProcessingException e) {
            try {
                UpbitError upbitError = objectMapper.readValue(result, UpbitError.class);
                waitListResponse = WaitListResponse.builder()
                        .status(upbitError.getError().getName())
                        .message(upbitError.getError().getMessage())
                        .build();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        return waitListResponse;
    }

}
