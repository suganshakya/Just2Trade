package com.sugan.just2trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sugan.just2trade.Rest;

public class Account {
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("trade_platform")
    private String tradePlatform;

    @JsonProperty("margin_type")
    private String marginType;

    private String restriction;

    @JsonProperty("daytrades_count")
    int dayTradesCount;

    double cash;

    @JsonProperty("day_trading_buying_power")
    double dayTradingBuyingPower;

    @JsonProperty("margin_buying_power")
    double marginBuyingPower;

    @JsonProperty("non_margin_buying_power")
    double nonMarginBuyingPower;

    @JsonProperty("account_value_total")
    double accountValueTotal;

    @JsonProperty("position_market_value")
    double positionMarketValue;

    @JsonProperty("unsettled_cash")
    double unsettledCash;

    public Account() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTradePlatform() {
        return tradePlatform;
    }

    public void setTradePlatform(String tradePlatform) {
        this.tradePlatform = tradePlatform;
    }

    public String getMarginType() {
        return marginType;
    }

    public void setMarginType(String marginType) {
        this.marginType = marginType;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public int getDayTradesCount() {
        return dayTradesCount;
    }

    public void setDayTradesCount(int dayTradesCount) {
        this.dayTradesCount = dayTradesCount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getDayTradingBuyingPower() {
        return dayTradingBuyingPower;
    }

    public void setDayTradingBuyingPower(double dayTradingBuyingPower) {
        this.dayTradingBuyingPower = dayTradingBuyingPower;
    }

    public double getMarginBuyingPower() {
        return marginBuyingPower;
    }

    public void setMarginBuyingPower(double marginBuyingPower) {
        this.marginBuyingPower = marginBuyingPower;
    }

    public double getNonMarginBuyingPower() {
        return nonMarginBuyingPower;
    }

    public void setNonMarginBuyingPower(double nonMarginBuyingPower) {
        this.nonMarginBuyingPower = nonMarginBuyingPower;
    }

    public double getAccountValueTotal() {
        return accountValueTotal;
    }

    public void setAccountValueTotal(double accountValueTotal) {
        this.accountValueTotal = accountValueTotal;
    }

    public double getPositionMarketValue() {
        return positionMarketValue;
    }

    public void setPositionMarketValue(double positionMarketValue) {
        this.positionMarketValue = positionMarketValue;
    }

    public double getUnsettledCash() {
        return unsettledCash;
    }

    public void setUnsettledCash(double unsettledCash) {
        this.unsettledCash = unsettledCash;
    }

    @Override
    public String toString() {
        return Rest.getJsonString(this);
    }
}
