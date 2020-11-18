package com.line.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Category;
import com.line.store.entity.Subcategory;

@Repository
public interface SubcategoryDao extends JpaRepository<Subcategory, String>{
	
	List<Subcategory> findByActiveFg(String activeFg);
	
	List<Subcategory> findByCategory(Category category);

}
