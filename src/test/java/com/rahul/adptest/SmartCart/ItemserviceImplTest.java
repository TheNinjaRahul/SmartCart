package com.rahul.adptest.SmartCart;


import com.rahul.adptest.SmartCart.enums.Category;
import com.rahul.adptest.SmartCart.helper.TaxTypeBean;
import com.rahul.adptest.SmartCart.model.FinalBill;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.repository.ItemRepository;
import com.rahul.adptest.SmartCart.service.ItemService;
import com.rahul.adptest.SmartCart.service.ItemserviceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ItemserviceImplTest {

    @InjectMocks
    ItemserviceImpl itemService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    TaxTypeBean taxTypeBean;

    @Test
    public void calculateCartTest(){
        Item item=new Item("1","ParleG",new BigDecimal("200.00"), Category.C);
        Item item1=new Item("2","Mobile",new BigDecimal("200.00"), Category.A);
        Item item2=new Item("3","Laptop",new BigDecimal("1000.00"), Category.B);

        itemService.addToCart(item,1);
        itemService.addToCart(item1,1);
        itemService.addToCart(item2,1);
        Mockito.when(taxTypeBean.getTaxForCategory(Category.C)).thenReturn(BigDecimal.ZERO);
        Mockito.when(taxTypeBean.getTaxForCategory(Category.A)).thenReturn(BigDecimal.valueOf(20));
        Mockito.when(taxTypeBean.getTaxForCategory(Category.B)).thenReturn(BigDecimal.valueOf(10));


        FinalBill finalBill=itemService.calculateCart();
        Assert.assertEquals(new BigDecimal("140.00"),finalBill.getTotalTax());
        Assert.assertEquals(new BigDecimal("1540.00"),finalBill.getToalPrice());
    }

}
