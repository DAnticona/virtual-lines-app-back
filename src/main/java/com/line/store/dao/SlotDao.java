package com.line.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Slot;

@Repository
public interface SlotDao extends JpaRepository<Slot, String>{

}
