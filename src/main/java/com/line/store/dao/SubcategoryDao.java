package com.line.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Subcategory;
import com.line.store.entity.id.SubcategoryId;

@Repository
public interface SubcategoryDao extends JpaRepository<Subcategory, SubcategoryId>{

}
