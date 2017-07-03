package com.nagrand.calculate.entity;

/**
 * @author Yeshufeng
 * @title
 * @date 2017/7/3
 */
public class AsynOrder {
    private String orderId;
    private Double amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
