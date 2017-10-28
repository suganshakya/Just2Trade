package com.sugan.just2trade;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sugan.just2trade.model.Account;
import com.sugan.just2trade.model.Position;
import com.sugan.just2trade.model.TradeHistory;
import com.sugan.just2trade.model.TradeOrder;
import com.sugan.just2trade.model.TradeResponse;
import com.sugan.just2trade.model.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Just2TradeClient {

    private String accessToken;

    private static final String JUST2TRADE_API = "https://api.just2trade.com";

    private Rest rest;

    public Just2TradeClient(String accessToken) {
        this.accessToken = accessToken;
        rest = new Rest(JUST2TRADE_API);
        rest.setAccessToken(accessToken);
    }


    public UserInfo getUserInfo(){
        UserInfo userInfo = rest.get("/userinfo", UserInfo.class);
        return  userInfo;
    }

    public List<Account> getAccounts(){
        // If you account model is different, collect jsonNode and map to your class
        Account[]  accounts =  rest.get("/accounts", Account[].class);
        return Arrays.asList(accounts);
    }

    public List<Position> getPositions(String accountNumber){
        Position[] positions = rest.get("/accounts/" + accountNumber + "/positions", Position[].class);
        return Arrays.asList(positions);
    }

    public List<TradeHistory> getTrades(String accountNumber){
        String url = "/accounts/" + accountNumber + "/trades";
        JsonNode tradeNode = rest.get(url, JsonNode.class);
        int count = tradeNode.get("count").asInt();

        List<TradeHistory> histories = getTradeHistories(tradeNode);
        int numberOfTrade = histories.size();

        if(count > numberOfTrade){
            JsonNode remainingTrade = rest.get(url + "?filter.limit=" + (count-numberOfTrade) + "&filter.skip=" + numberOfTrade, JsonNode.class);
            histories.addAll(getTradeHistories(remainingTrade));
        }
        return histories;
    }

    private List<TradeHistory> getTradeHistories(JsonNode tradeNode){
        ArrayNode tradeArrayNode = (ArrayNode) tradeNode.get("trades");
        List<TradeHistory> histories = new ArrayList<>();
        for(JsonNode node: tradeArrayNode){
            TradeHistory history = Rest.parseJson(node, TradeHistory.class);
            histories.add(history);
        }
        return histories;
    }

    private TradeResponse trade(TradeOrder order){
        JsonNode validateResponse = trade(order, true);
        if(validateResponse.get("is_valid").asBoolean()){
            JsonNode tradeResponseNode = trade(order, false);
            TradeResponse response = Rest.parseJson(tradeResponseNode, TradeResponse.class);
            return response;
        }
        throw new Just2TradeException(validateResponse.get("validation_message").asText());
    }

    private JsonNode trade(TradeOrder order, boolean isValidate){
        String url = "/orders/" + (isValidate? "validate": "trade");
        JsonNode response = rest.post(url, order, JsonNode.class);
        return response;
    }

    public static class Just2TradeException extends RuntimeException{
        public Just2TradeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("conf.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String accessToken = properties.getProperty("access_token");

        Just2TradeClient client = new Just2TradeClient(accessToken);
        UserInfo info = client.getUserInfo();
        String accountNumber = info.getAccounts().get(0).getAccountNumber();

        List<Position> positions = client.getPositions(accountNumber);
        System.out.println(positions);
        System.out.println(client.getAccounts());

        List<TradeHistory> histories = client.getTrades(accountNumber);
        System.out.println(histories.size());
        System.out.println(histories);

        TradeOrder tradeOrder = new TradeOrder(accountNumber, "BAC", 1, TradeOrder.OrderType.MARKET, TradeOrder.Side.BUY);

        System.out.println(client.trade(tradeOrder, true));
    }
}
