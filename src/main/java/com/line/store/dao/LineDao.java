package com.line.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Line;

@Repository
public interface LineDao extends JpaRepository<Line, String>{

}
