package com.line.store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.line.store.entity.Line;
import com.line.store.entity.Slot;
import com.line.store.entity.User;

@Repository
public interface SlotDao extends JpaRepository<Slot, String> {

	List<Slot> findByLine(Line line);

	@Query("SELECT s FROM Slot s WHERE s.line = :line and s.user = :user and s.activeFg = :activeFg")
	Optional<Slot> findByLineUserActiveFg(Line line, User user, String activeFg);
	
	@Query(value = "SELECT count(*) FROM slot WHERE line_id = :lineId and active_fg = :activeFg", nativeQuery = true)
	Optional<Integer> countByActiveFgByLineId(String lineId, String activeFg);

}
