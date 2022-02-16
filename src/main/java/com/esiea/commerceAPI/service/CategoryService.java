package com.esiea.commerceAPI.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.esiea.commerceAPI.modele.Category;
import com.esiea.commerceAPI.repository.CategoryRepository;

@Service
public class CategoryService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategorie(long id) throws NotFOundException {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent()) 
			return category.get();
		throw new NotFOundException();
	}

	public Category create(Category category) throws NotAllowedException {
		if(category.getId() == null)
			return categoryRepository.save(category);
		throw new NotAllowedException();
	}

	public void delete(Long id) throws NotFOundException {
		try {
			categoryRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new NotFOundException();
		}
	}

	public Category replace(Category category) throws NotFOundException {
		try {
			getCategorie(category.getId());
			return categoryRepository.save(category);
		} catch (NotFOundException e) {
			throw new NotFOundException();
		}
		
		
	}

	
	
	
}
