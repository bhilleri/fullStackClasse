package com.esiea.commerceAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.repository.ProductRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Iterable<Product> getProducts(){
		return productRepository.findAll();
	}

	public Product getProduit(long id) throws NotFOundException {
		java.util.Optional<Product> resultat = productRepository.findById(id);
		if(resultat.isPresent()) {
			return resultat.get();
		}
		else {
			throw new NotFOundException();
		}
	}
}
