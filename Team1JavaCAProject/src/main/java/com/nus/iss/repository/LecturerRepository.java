package com.nus.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nus.iss.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
	
	@Query("Select l from Lecturer l where l.lectId = :lid")
	Lecturer findByLecturerId(@Param("lid") int lid);
}
