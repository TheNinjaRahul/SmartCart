package com.rahul.adptest.SmartCart.repository;

import com.rahul.adptest.SmartCart.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ItemRepository extends MongoRepository<Item,String> {

}
