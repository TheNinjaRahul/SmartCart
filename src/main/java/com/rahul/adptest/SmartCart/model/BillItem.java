package com.rahul.adptest.SmartCart.model;

import java.math.BigDecimal;
import java.util.Objects;

public class BillItem {
    private Item item;
    private int quantity;
    private BigDecimal finalPrice;


    public BillItem(Item item, int quantity, BigDecimal finalPrice) {
        this.item = item;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public BillItem() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillItem billItem = (BillItem) o;
        return Objects.equals(item, billItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
