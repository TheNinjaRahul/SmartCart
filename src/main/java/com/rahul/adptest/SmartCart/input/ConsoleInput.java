package com.rahul.adptest.SmartCart.input;

import com.rahul.adptest.SmartCart.model.ItemMetaData;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements IItemScanner {
 private Scanner scn=new Scanner(System.in);

    @Override
    public ItemMetaData scanItem() {
        System.out.println("Enter Item no id");
        String id=scn.next();
        System.out.println("Enter quantity");
        int quantity=scn.nextInt();
        return new ItemMetaData(id,quantity);
    }
}
