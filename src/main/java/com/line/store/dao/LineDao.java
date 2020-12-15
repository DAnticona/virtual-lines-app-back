package com.line.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Line;
import com.line.store.entity.Store;

@Repository
public interface LineDao extends JpaRepository<Line, String>{
	
	List<Line> findByStore(Store store);

}
