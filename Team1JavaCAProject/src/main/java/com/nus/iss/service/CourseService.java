package com.nus.iss.service;

import java.util.ArrayList;

import com.nus.iss.model.Course;

public interface CourseService {
	
	public ArrayList<Course> findAllCourses();
	
	public void addCourse(Course c);
	
	public Course findById(String c);
}
