package com.esiea.commerceAPI.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.esiea.commerceAPI.modele.Product;
import com.esiea.commerceAPI.repository.ProductRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	public Iterable<Product> getProducts(){
		return productRepository.findAll();
	}

	public Product getProduct(long id) throws NotFOundException {
		java.util.Optional<Product> resultat = productRepository.findById(id);
		if(resultat.isPresent()) {
			return resultat.get();
		}
		else {
			throw new NotFOundException();
		}
	}

	public Product create(Product product) throws NotAllowedException {
		// verification de la donnée
		// Insère l'objet et le retourne
		if(product.getId() == null)
			return productRepository.save(product);
		throw new NotAllowedException();
	}
	
	public Product update(Product product) throws NotFOundException {
		// verification de la donnée
		// Insère l'objet et le retourne
		getProduct(product.getId());
		return productRepository.save(product);
	}

	public void delete(long id) throws NotFOundException {
		// TODO Auto-generated method stub
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			logger.warn(id + " not found.");
			throw new NotFOundException();
		}
	}
	
}
