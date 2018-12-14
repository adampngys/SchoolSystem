package com.nus.iss.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.iss.model.Student;
import com.nus.iss.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	@Resource
	private StudentRepository studentRepository;

	@Override
	public Student findStudentById(int sid) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentById(sid);
	}

}
