package com.line.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{
	
	List<Role> findByClientFg(String clientFg);
	

}
