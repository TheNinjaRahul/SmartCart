package com.rahul.adptest.SmartCart.output;

import com.rahul.adptest.SmartCart.model.BillItem;
import com.rahul.adptest.SmartCart.model.FinalBill;
import org.springframework.stereotype.Component;

@Component
public class ConsoleBillGenerator implements IBillGeneratorr {
    @Override
    public boolean generateOutput(FinalBill finalBill) {
        System.out.println("Item_Name Quantity Item_Price Total_Price");
        for(BillItem bi:finalBill.getList())
        System.out.println(bi.getItem().getName()+" "+bi.getQuantity()+" "+" "+bi.getItem().getPrice()+" "+bi.getFinalPrice());
        System.out.println("Total cost "+finalBill.getToalPrice());
        System.out.println("Tax applied "+finalBill.getTotalTax());
        return true;
    }
}
