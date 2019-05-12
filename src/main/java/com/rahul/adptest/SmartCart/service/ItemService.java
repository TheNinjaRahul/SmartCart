package com.rahul.adptest.SmartCart.service;

import com.rahul.adptest.SmartCart.model.FinalBill;
import com.rahul.adptest.SmartCart.model.Item;

import java.util.Optional;

public interface ItemService {
    public void addToCart(Item item,int q);
    public void addItem(Item item);
    public Item getItemById(String id);
    public FinalBill calculateCart();
    public boolean clearCart();
}
