package com.esiea.commerceAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esiea.commerceAPI.modele.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
