package com.rahul.adptest.SmartCart.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.adptest.SmartCart.enums.Category;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class TaxTypeBean {

    private HashMap<Category, BigDecimal> tax=new HashMap<>();

    public TaxTypeBean(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> list = mapper.readValue(new ClassPathResource(
                    "category.json").getFile(), new TypeReference<List<Map<String, Object>>>() {
            });
            for(Map<String,Object> cat:list){
                tax.put(Category.valueOf((String)cat.get("name")),new BigDecimal((String)cat.get("value")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public HashMap<Category, BigDecimal> getTax() {
        return tax;
    }

    public void setTax(HashMap<Category, BigDecimal> tax) {
        this.tax = tax;
    }


    public BigDecimal getTaxForCategory(Category c){
        return tax.get(c);
    }
}
