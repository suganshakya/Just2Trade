package com.sugan.just2trade.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sugan.just2trade.Rest;

public class Position {
    public enum SecurityType {
        COMMON_STOCK, PREFERRED_STOCK, OPTION;

        @JsonCreator
        public static SecurityType from(String securityType) {
            return SecurityType.valueOf(securityType.toUpperCase());
        }

    }

    private String symbol;

    private int quantity;

    @JsonProperty("average_open_price")
    private double averageOpenPrice;

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("security_type")
    private SecurityType securityType;

    @JsonProperty("lot_size")
    private int lotSize;

    //Getter Setter

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

    public double getAverageOpenPrice() {
        return averageOpenPrice;
    }

    public void setAverageOpenPrice(double averageOpenPrice) {
        this.averageOpenPrice = averageOpenPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public SecurityType getSecurityType() {
        return securityType;
    }

    public void setSecurityType(SecurityType securityType) {
        this.securityType = securityType;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public String toString() {
        return Rest.getJsonString(this);
    }
}
