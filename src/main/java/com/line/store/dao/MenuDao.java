package com.line.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Menu;

@Repository
public interface MenuDao extends JpaRepository<Menu, Integer>{

}
