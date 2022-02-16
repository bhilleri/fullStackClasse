package com.esiea.commerceAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esiea.commerceAPI.modele.Category;
import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.service.CategoryService;
import com.esiea.commerceAPI.service.NotAllowedException;
import com.esiea.commerceAPI.service.NotFOundException;
import com.esiea.commerceAPI.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/private/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GetMapping("")
	public Iterable<Category> getCategories(){
		return categoryService.getCategories();
	}
	
	@GetMapping("/{categoryID}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryID") long id) {
		try {
			Category category = categoryService.getCategorie(id);
			return new ResponseEntity<Category>(category, HttpStatus.OK );
		} catch (NotFOundException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND );
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		try {
			Category newCategory = categoryService.create(category);
			return new ResponseEntity<Category>(newCategory, HttpStatus.OK);
		}catch (NotAllowedException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id){
		try {
			categoryService.delete(id);
			return new ResponseEntity<Category>(HttpStatus.OK);
		} catch (NotFOundException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("")
	public ResponseEntity<Category> replaceCategory(@RequestBody Category category)
	{
		try {
			Category updatedCategory = categoryService.replace(category);
			return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
		} catch (NotFOundException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{idCategory}/{idProduct}")
	public ResponseEntity<Category> addProductToCategory(
			@PathVariable(name = "idCategory") Integer idCategory,
			@PathVariable(name = "idProduct") Integer idProduct){
			
		try {
			Category category = categoryService.getCategorie(idCategory);
			Product product = productService.getProduct(idProduct);
			category.addProduct(product);
			categoryService.replace(category);
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		} catch (NotFOundException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		
	}
}
