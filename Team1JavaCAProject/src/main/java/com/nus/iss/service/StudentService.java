package com.nus.iss.service;

import org.springframework.stereotype.Service;

import com.nus.iss.model.Student;

@Service
public interface StudentService {

	public Student findStudentById(int sid);

}
