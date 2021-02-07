package com.line.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Submenu;

@Repository
public interface SubmenuDao extends JpaRepository<Submenu, Integer>{

}
