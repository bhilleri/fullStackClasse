package com.esiea.commerceAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esiea.commerceAPI.modele.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public Optional<Product> findByName(String name);
}
