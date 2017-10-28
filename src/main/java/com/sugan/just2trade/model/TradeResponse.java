package com.sugan.just2trade.model;

import com.sugan.just2trade.Rest;

public class TradeResponse {
    private boolean success;

    private String data;    // order id

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Rest.getJsonString(this);
    }
}
