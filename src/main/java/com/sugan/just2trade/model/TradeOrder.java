package com.sugan.just2trade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sugan.just2trade.Rest;

public class TradeOrder {
    public enum TimeInForce {
        DAY, GOOD_TILL_CANCEL, GOOD_TILL_CROSSING;

        @JsonCreator
        public TimeInForce from(String timeInForce) {
            return TimeInForce.valueOf(timeInForce.toUpperCase());
        }
    }

    public enum OrderType {
        MARKET, LIMIT, STOP, LIMIT_STOP;

        @JsonCreator
        public OrderType from(String orderType) {
            return OrderType.valueOf(orderType.toUpperCase());
        }
    }

    public enum Side {
        BUY, SELL, SHORT, COVER;

        @JsonCreator
        public Side from(String side) {
            switch (side) {
                case "buy":
                    return BUY;
                case "sell":
                    return SELL;
                case "sell_short":
                    return SHORT;
                case "buy_to_cover":
                    return COVER;
                default:
                    throw new RuntimeException("Invalid Side: " + side);
            }
        }
    }

    @JsonProperty("account_number")
    private String accountNumber;

    private String symbol;

    private int quantity;

    private double price;

    private double stopPrice;

    @JsonProperty("time_in_force")
    private TimeInForce timeInForce;

    @JsonProperty("order_type")
    private OrderType orderType;

    private Side side;

    public TradeOrder() {
    }

    public TradeOrder(String accountNumber, String symbol, int quantity, OrderType orderType, Side side) {
        this.accountNumber = accountNumber;
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderType = orderType;
        this.side = side;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(double stopPrice) {
        this.stopPrice = stopPrice;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return Rest.getJsonString(this);
    }
}
