package com.rahul.adptest.SmartCart;

import com.rahul.adptest.SmartCart.enums.Category;
import com.rahul.adptest.SmartCart.input.ConsoleInput;
import com.rahul.adptest.SmartCart.input.IItemScanner;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.model.ItemMetaData;
import com.rahul.adptest.SmartCart.output.IBillGeneratorr;
import com.rahul.adptest.SmartCart.processor.ItemProcessor;
import com.rahul.adptest.SmartCart.service.ItemService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

@RunWith(MockitoJUnitRunner.class)
public class ItemProcessorTests {

    @Mock
    private IItemScanner itemScanner;
    @Mock
    private IBillGeneratorr billGeneratorr;
    @Mock
    private ItemService itemService;
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testProcessItem(){
        ItemProcessor itemProcessor=new ItemProcessor(itemScanner,billGeneratorr,itemService);
        Mockito.when(itemScanner.scanItem()).thenReturn(new ItemMetaData("1",1));
        systemInMock.provideLines("1","2","4");
        itemProcessor.processItem();
        Mockito.verify(itemScanner,Mockito.times(1)).scanItem();
        Mockito.verify(itemService,Mockito.times(1)).getItemById(Mockito.anyString());
        Mockito.verify(itemService,Mockito.times(1)).addToCart(Mockito.any(),Mockito.anyInt());
        Mockito.verify(itemService,Mockito.times(1)).calculateCart();
        Mockito.verify(billGeneratorr,Mockito.times(1)).generateOutput(Mockito.any());
        Mockito.verify(itemService,Mockito.times(2)).clearCart();
        Assert.assertEquals("1. Add item\r\n" +
                "2. Generate Bill\r\n" +
                "3. Cancel Order\r\n" +
                "4. Exit\r\n" +
                "1. Add item\r\n" +
                "2. Generate Bill\r\n" +
                "3. Cancel Order\r\n" +
                "4. Exit\r\n" +
                "1. Add item\r\n" +
                "2. Generate Bill\r\n" +
                "3. Cancel Order\r\n" +
                "4. Exit\r\n", systemOutRule.getLog());
    }
}
