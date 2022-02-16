package com.esiea.commerceAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.repository.ProductRepository;

@Component
public class DataPopular implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... args) throws Exception {
//		Product p = new Product(Long.valueOf(1), "product1", "a description", Integer.valueOf(500));
//		Product p2 = new Product(Long.valueOf(2), "product2", "an useless item", Integer.valueOf(15896254));
//		// TODO Auto-generated method stub
//		productRepository.save(p);
//		productRepository.save(p2);
	}
	
}
