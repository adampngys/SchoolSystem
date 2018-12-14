package com.nus.iss.service;

import java.util.ArrayList;

import com.nus.iss.model.Course;
import com.nus.iss.model.StdCourse;

public interface AdminEnrolmentService {

	ArrayList<Course> findAllCourse();

	ArrayList<StdCourse> findStudentCourse();
	
	boolean enrolmentReject(int StdCourseId);

	boolean enrolmentApprove(int StdCourseId);

	ArrayList<StdCourse> findAllApply();

}
