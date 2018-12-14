package com.nus.iss.service;

import java.util.ArrayList;

import com.nus.iss.model.Course;

public interface AdminCourseService {

	ArrayList<Course> findAllCourses();

	void removeCourse(Course c);

	Course updateCourse(Course c);

	Course createCourse(Course c);

	Course findCourse(String id);

}
