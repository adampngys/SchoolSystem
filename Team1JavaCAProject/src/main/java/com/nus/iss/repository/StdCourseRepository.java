package com.nus.iss.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nus.iss.model.StdCourse;

public interface StdCourseRepository extends JpaRepository<StdCourse, Integer> {

	/*
	 * @Query("from StdCourse sc where sc.student.id=:sid") ArrayList<StdCourse>
	 * findGradesByStd(@Param("sid") int sid);
	 */
	@Query("from StdCourse sc where sc.student.id=:sid")
	ArrayList<StdCourse> findStdCourseByStdId(@Param("sid") int sid);

	@Query("SELECT sc from StdCourse sc where sc.course.courseId = :cid and sc.status= 'approve'")
	ArrayList<StdCourse> findStudentsByCID(@Param("cid") String cid);

	@Query("SELECT sc from StdCourse sc where sc.id = :id")
	StdCourse findOne(@Param("id") Integer id);

	@Query(value = "FROM StdCourse sc WHERE sc.status=:sts")
	List<StdCourse> findAllWithStatus(@Param("sts") String status);

}
