package com.nus.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.iss.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

}
