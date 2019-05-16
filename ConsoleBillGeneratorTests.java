package com.rahul.adptest.SmartCart;

import com.rahul.adptest.SmartCart.enums.Category;
import com.rahul.adptest.SmartCart.model.BillItem;
import com.rahul.adptest.SmartCart.model.FinalBill;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.output.ConsoleBillGenerator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.math.BigDecimal;
import java.util.Arrays;

public class ConsoleBillGeneratorTests {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testGenerateOutput(){
        BillItem billItem=new BillItem(new Item("1","aaa",new BigDecimal(50), Category.A),1,new BigDecimal(60));
        FinalBill finalBill=new FinalBill(Arrays.asList(billItem),new BigDecimal(50),new BigDecimal(10));
        ConsoleBillGenerator consoleBillGenerator=new ConsoleBillGenerator();
        boolean result=consoleBillGenerator.generateOutput(finalBill);
        Assert.assertEquals("Item_Name Quantity Item_Price Total_Price\r\n" +
                "aaa 1  50 60\r\n" +
                "Total cost 50\r\n" +
                "Tax applied 10\r\n",systemOutRule.getLog());
        Assert.assertTrue(result);
    }
}
