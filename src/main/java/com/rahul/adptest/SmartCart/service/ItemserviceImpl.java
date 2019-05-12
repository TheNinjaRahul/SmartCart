package com.rahul.adptest.SmartCart.service;

import com.rahul.adptest.SmartCart.helper.TaxTypeBean;
import com.rahul.adptest.SmartCart.model.BillItem;
import com.rahul.adptest.SmartCart.model.FinalBill;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ItemserviceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TaxTypeBean taxTypeBean;

    HashMap<Item, Integer> cart = new HashMap<>();

    final BigDecimal oneHunderd = new BigDecimal("100.00");


    @Override
    public void addToCart(Item item, int q) {

        if (cart.containsKey(item)) {
            Integer i = cart.get(item);
            i = i + q;
            cart.put(item, i);
        } else {
            cart.put(item, q);
        }
    }

    @Override
    public void addItem(Item item) {
        itemRepository.insert(item);
    }

    @Override
    public Item getItemById(String id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public FinalBill calculateCart() {
        BigDecimal totalPrice = new BigDecimal("0.00");
        BigDecimal totalTax = new BigDecimal("0.00");
        List<BillItem> list = new ArrayList<>();

        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {

            BigDecimal price = new BigDecimal("0.00");
            price = entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
            BigDecimal tax = (taxTypeBean.getTaxForCategory(entry.getKey().getCategory()).multiply(price)).divide(oneHunderd);
            totalTax=totalTax.add(tax);
            price=price.add(tax);
            totalPrice=totalPrice.add(price);
            BillItem billItem = new BillItem(entry.getKey(), entry.getValue(), price);
            list.add(billItem);
        }
        FinalBill finalBill = new FinalBill(list, totalPrice, totalTax);
        return finalBill;
    }

    @Override
    public boolean clearCart() {
        cart.clear();
        return true;
    }


}
