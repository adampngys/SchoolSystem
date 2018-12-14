package com.nus.iss.service;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nus.iss.model.Course;
import com.nus.iss.repository.CourseRepository;
@Service
public class AdminCourseServiceimpl implements AdminCourseService {
	@Resource
	private CourseRepository courseRepository;
	
	
	@Override
	public ArrayList<Course> findAllCourses() {
		return (ArrayList<Course>) courseRepository.findAll();
	}
	
	@Override
	@Transactional
	public Course findCourse(String id) {
		System.out.println("Course ID"+id);
		
		Course course = courseRepository.findById(id).get();
		System.out.println(course.toString());
		return course;
		
	}
	
		
	@Override
	@Transactional
	public Course createCourse(Course c) {
		return courseRepository.saveAndFlush(c);
	}
	@Override
	@Transactional
	public Course updateCourse(Course c) {
		return courseRepository.saveAndFlush(c);
	}

	@Override
	@Transactional
	public void removeCourse(Course c) {
		courseRepository.delete(c);
	}
	
	
	

}
