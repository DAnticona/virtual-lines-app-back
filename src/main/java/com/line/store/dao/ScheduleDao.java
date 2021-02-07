package com.line.store.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Schedule;
import com.line.store.entity.Store;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, String> {
	
	List<Schedule> findByStore(Store store);
	
	@Query(value = "SELECT s FROM Schedule s WHERE s.store = :store AND s.date = :date")
	List<Schedule> findByStoreDate(Store store, LocalDate date);

}
