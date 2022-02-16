package com.esiea.commerceAPI.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	
	@Column(name = "categoryname")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, // performance            
			cascade = { CascadeType.PERSIST, // création                    
					CascadeType.MERGE }) // modification    
			@JoinTable(
					name = "category_product", // nom de la table de jointure
					// clé étrangère dans la table de jointure correspondant à la clé primaire            // de la classe courante (category)
					joinColumns = @JoinColumn(name = "category_id"),
					// clé étrangère dans la table de jointure correspondant à la clé primaire            // de la classe en relation (product)            
					inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category() {
		
	}
	public void addProduct(Product product) {
		products.add(product);
	}
	public void removeProduct(Product product) {
		products.remove(product);
	}

	
}
