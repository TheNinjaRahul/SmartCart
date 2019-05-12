package com.rahul.adptest.SmartCart.processor;

import com.rahul.adptest.SmartCart.input.IItemScanner;
import com.rahul.adptest.SmartCart.model.FinalBill;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.model.ItemMetaData;
import com.rahul.adptest.SmartCart.output.IBillGeneratorr;
import com.rahul.adptest.SmartCart.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@Component
public class ItemProcessor {

    private IItemScanner itemScanner;
    private IBillGeneratorr billGeneratorr;
    private ItemService itemService;
    private Scanner scn=new Scanner(System.in);

    protected ItemProcessor(IItemScanner itemScanner, IBillGeneratorr billGeneratorr, ItemService itemService){
        this.itemScanner=itemScanner;
        this.billGeneratorr=billGeneratorr;
        this.itemService=itemService;
    }

    public void processItem(){
        int input;
        do{
            System.out.println("1. Add item");
            System.out.println("2. Generate Bill");
            System.out.println("3. Cancel Order");
            System.out.println("4. Exit");
            input=scn.nextInt();
            switch(input){
                case 1:
                    ItemMetaData itemMetaData=itemScanner.scanItem();
                    Item item=itemService.getItemById(itemMetaData.getId());
                    itemService.addToCart(item,itemMetaData.getQuantity());
                    break;
                case 2:
                    FinalBill finalBill=itemService.calculateCart();
                    billGeneratorr.generateOutput(finalBill);
                    itemService.clearCart();
                    break;
                case 3:
                case 4:
                    itemService.clearCart();
                    break;

                default:
                    System.out.println("Enter valid input");
                    break;

            }
        }while(input!=4);
    }


}
