package com.line.store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Category;
import com.line.store.entity.Store;

@Repository
public interface StoreDao extends JpaRepository<Store, String>{

	@Query("SELECT s FROM Store s ORDER BY publicName asc")
	List<Store> findAll();
	
	Page<Store> findAll(Pageable pageable);
	
	
	List<Store> findByCategory(Category category);
	
	@Query("SELECT s FROM Store s where publicName like %:publicName%")
	List<Store> searchByPublicName(String publicName);
}
