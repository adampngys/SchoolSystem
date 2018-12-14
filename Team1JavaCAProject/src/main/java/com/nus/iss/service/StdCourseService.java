package com.nus.iss.service;

import java.util.ArrayList;

import com.nus.iss.model.StdCourse;

public interface StdCourseService {

	/* public ArrayList<StdCourse> listGrades(int sid); */

	public StdCourse addCourse(StdCourse sc);

	public ArrayList<StdCourse> listStudentCourse(int sid);

	public StdCourse findStdCourse(int StdCourseId);

	public void removeStdCourse(StdCourse sc);
}
