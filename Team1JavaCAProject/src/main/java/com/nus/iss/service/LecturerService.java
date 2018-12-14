package com.nus.iss.service;

import java.util.ArrayList;

import com.nus.iss.model.Lecturer;
import com.nus.iss.model.LecturerCourse;
import com.nus.iss.model.StdCourse;

public interface LecturerService {


ArrayList<LecturerCourse> findAllCoursesByLID(Integer lid);

double getGPA(int grade);



public Lecturer findByLecturerId(int lid);

ArrayList<StdCourse> findAllStudentsByCID(String cid);

StdCourse findStdCourse(Integer id);

StdCourse updategrade(StdCourse stdcourse);

String getGrade(Double gpa);

}
