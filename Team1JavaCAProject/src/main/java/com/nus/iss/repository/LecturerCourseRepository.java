package com.nus.iss.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nus.iss.model.LecturerCourse;

public interface LecturerCourseRepository extends JpaRepository<LecturerCourse, Integer> {

	@Query("SELECT lc from LecturerCourse lc where lc.lecturer.lectId = :lid")
	ArrayList<LecturerCourse> findCoursesByLID(@Param("lid") Integer lid);
	// @Param("lid") Integer lid

}
