package com.line.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	List<Category> findByActiveFg(String activeFg);
	

}
