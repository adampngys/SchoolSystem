package com.nus.iss.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nus.iss.model.Course;
import com.nus.iss.repository.CourseRepository;
@Service
public class CourseServiceImpl implements CourseService {
	
	@Resource
	private CourseRepository courseRepository;

	@Override
	public ArrayList<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return (ArrayList<Course>) courseRepository.findAll();
	}

	@Override
	public void addCourse(Course c) {
		courseRepository.saveAndFlush(c);
	}

	@Override
	public Course findById(String c) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(c).get();
		return course;
	}

}
