package com.rahul.adptest.SmartCart;


import com.rahul.adptest.SmartCart.input.ConsoleInput;
import com.rahul.adptest.SmartCart.model.ItemMetaData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class ConsoleInputTests {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void testScanItem(){
        ConsoleInput consoleInput=new ConsoleInput();
        systemInMock.provideLines("1","5");
        ItemMetaData itemMetaData=consoleInput.scanItem();
        Assert.assertEquals("1",itemMetaData.getId());
        Assert.assertEquals(5,itemMetaData.getQuantity());
    }
}
