package com.nus.iss.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Student;

@Service
public interface AdminStudentService {
	ArrayList<Student> findAllStudents();

	/* Student findStudent(String id); */

	Student createStudent(Student s);

	Student updateStudent(Student s);

	void removeStudent(Student s);

	Student findStudent(Integer id);

	Student CreateUserAndStudent(CapsUser u1, Student s1);

	boolean DeleteUserAndStudent(int stdId);

	Student UpdateUserAndStudent(String pwd, Student s1);

	Student FindById(int stdId);

	List<Student> FindAll();

}
