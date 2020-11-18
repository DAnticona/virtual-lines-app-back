package com.line.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Store;

@Repository
public interface StoreDao extends JpaRepository<Store, String>{

	List<Store> findByActiveFg(String activeFg);
}
