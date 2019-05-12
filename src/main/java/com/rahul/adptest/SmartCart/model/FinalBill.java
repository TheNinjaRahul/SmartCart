package com.rahul.adptest.SmartCart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
public class FinalBill {
        @Id
        String id;
        private List<BillItem> list;
        private BigDecimal totalTax;
        private BigDecimal toalPrice;

    public FinalBill(List<BillItem> list, BigDecimal toalPrice, BigDecimal totalTax) {
        this.list = list;
        this.toalPrice = toalPrice;
        this.totalTax=totalTax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public FinalBill() {
    }

    public List<BillItem> getList() {
        return list;
    }

    public void setList(List<BillItem> list) {
        this.list = list;
    }

    public BigDecimal getToalPrice() {
        return toalPrice;
    }

    public void setToalPrice(BigDecimal toalPrice) {
        this.toalPrice = toalPrice;
    }
}
