package com.esiea.commerceAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.service.NotFOundException;
import com.esiea.commerceAPI.service.ProductService;

import java.util.function.Predicate;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public Iterable <Product> getProducts(){
		return productService.getProducts();
	}
	
	public static Predicate<Product> isID(long id)
	{
	    return p -> p.getId() == id;
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		try {
			 Product p =  productService.getProduit(id);
			 return new  ResponseEntity<Product>(p, HttpStatus.OK);
		}
		catch (NotFOundException e){
			return new  ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		return productService.save(product);
	}
}

