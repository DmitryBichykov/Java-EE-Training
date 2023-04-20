package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private int customer_id;
    private List<Integer> goodsId;
    private float totalPrice;

    public Order(int id, int customer_id, float totalPrice) {
        this.id = id;
        this.customer_id = customer_id;
        this.totalPrice = totalPrice;
        this.goodsId = new ArrayList<>();
    }

    public void setGoodId(int goodId) {
        this.goodsId.add(goodId);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public List<Integer> getGoodsId() {
        return goodsId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", goodsId=" + goodsId +
                '}';
    }
}
