package com.line.store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Booking;
import com.line.store.entity.Schedule;
import com.line.store.entity.User;

@Repository
public interface BookingDao extends JpaRepository<Booking, String> {
	
	List<Booking> findBySchedule(Schedule schedule);
	
	@Query("SELECT b FROM Booking b WHERE b.schedule = :schedule and b.user = :user and b.activeFg = :activeFg")
	Optional<Booking> findByScheduleUserActiveFg(Schedule schedule, User user, String activeFg);

}
