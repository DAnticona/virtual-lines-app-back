package com.line.store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Store;
import com.line.store.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	public Optional<User> findByEmail(String email);
	
	public List<User> findByStore(Store store);
	
	public Page<User> findAll(Pageable pageable);
	
	@Query("SELECT u FROM User u where u.name like %:term% OR u.email like %:term%")
	List<User> searchByNameOrEmail(String term);
	

}
