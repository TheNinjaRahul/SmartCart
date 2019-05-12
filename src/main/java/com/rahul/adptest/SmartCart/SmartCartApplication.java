package com.rahul.adptest.SmartCart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.adptest.SmartCart.enums.Category;
import com.rahul.adptest.SmartCart.model.Item;
import com.rahul.adptest.SmartCart.processor.ItemProcessor;
import com.rahul.adptest.SmartCart.repository.ItemRepository;
import com.rahul.adptest.SmartCart.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class SmartCartApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(SmartCartApplication.class, args);
		ItemProcessor itemProcessor= (ItemProcessor) applicationContext.getBean("itemProcessor");
		itemProcessor.processItem();
	}

	@Bean
	public CommandLineRunner loadItems(ItemRepository itemRepository) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			try {
				List<Map<String, Object>> list = mapper.readValue(new ClassPathResource(
						"items.json").getFile(), new TypeReference<List<Map<String, Object>>>() {
				});
				List<Item> items = new ArrayList<>();
				int i=0;
				for (Map<String, Object> itemMap : list) {
					String id = String.valueOf(++i);
					Item item = new Item(id, (String) itemMap.get("name"), new BigDecimal((String) itemMap.get("price")), Category.valueOf((String) itemMap.get("category")));
					items.add(item);
				}
				itemRepository.saveAll(items);

			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
