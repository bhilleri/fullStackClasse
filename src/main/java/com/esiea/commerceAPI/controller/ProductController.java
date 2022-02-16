package com.esiea.commerceAPI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.service.NotAllowedException;
import com.esiea.commerceAPI.service.NotFOundException;
import com.esiea.commerceAPI.service.ProductService;

import java.util.function.Predicate;

@CrossOrigin()
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
			 Product p =  productService.getProduct(id);
			 return new  ResponseEntity<Product>(p, HttpStatus.OK);
		}
		catch (NotFOundException e){
			return new  ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/product/name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name){
		try {
			Product p = productService.getByName(name);
			return new ResponseEntity<Product>(p ,HttpStatus.OK);
		}catch(NotFOundException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			Product p =  productService.create(product);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (NotAllowedException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) throws NotFOundException
	{
		try {
			productService.delete(id);
			return new  ResponseEntity<>(HttpStatus.OK);
		} catch (NotFOundException e) {
			return new  ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> replaceProduct(@RequestBody Product product) {
		try {
			Product p = productService.update(product);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} catch (NotFOundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping("/product")
	public ResponseEntity<Product> partielReplaceProduct(@RequestBody Product product){
		try {
			Product existingProduct = productService.getProduct(product.getId());
			if(product.getName() != null && !product.getName().equals(existingProduct.getName()))
				existingProduct.setName(product.getName());
			if(product.getCost() != null && !product.getCost().equals(existingProduct.getCost()))
				existingProduct.setCost(product.getCost());
			if(product.getDescription() != null && !product.getDescription().equals(existingProduct.getDescription()))
				existingProduct.setDescription(product.getDescription());
			return new ResponseEntity<>(existingProduct, HttpStatus.OK);
		}catch (NotFOundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}

