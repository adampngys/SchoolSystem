package com.nus.iss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Student;
import com.nus.iss.repository.StudentRepository;
import com.nus.iss.repository.UserRepository;

@Service
public class AdminStudentServiceImpl implements AdminStudentService {
	@Resource
	private StudentRepository studentRepository;

	@Resource
	private UserRepository userRepository;

	@Override
	public ArrayList<Student> findAllStudents() {
		return (ArrayList<Student>) studentRepository.findAll();
	}

	@Override
	@Transactional
	public Student findStudent(Integer id) {
		System.out.println("ID" + id);

		Student student = studentRepository.findById(id).get();
		System.out.println(student.toString());
		return student;

	}

	@Override
	@Transactional
	public Student createStudent(Student s) {
		return studentRepository.saveAndFlush(s);
	}

	@Override
	@Transactional
	public Student updateStudent(Student s) {
		return studentRepository.saveAndFlush(s);
	}

	@Override
	@Transactional
	public void removeStudent(Student s) {
		studentRepository.delete(s);
	}

	@Override
	public List<Student> FindAll() {
		List<Student> stdList = null;
		try {
			stdList = studentRepository.findAll();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return stdList;
	}

	@Override
	public Student FindById(int stdId) {
		Student std = null;
		try {
			std = studentRepository.findById(stdId).get();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return std;
	}

	@Override
	public Student CreateUserAndStudent(CapsUser u1, Student s1) {
		if (u1 == null || s1 == null)
			return null;

		if (u1.getId() == null)
			return null;

		// check the existed user, if already exist then return null
		if (userRepository.findById(u1.getId()).isPresent()) {
			return null;
		}

		u1.setRole("student");

		// add foreign key info to student table
		s1.setUserInfo(u1);

		Student sRes = null;
		try {

			sRes = studentRepository.saveAndFlush(s1);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sRes;
	}

	@Override
	public Student UpdateUserAndStudent(String pwd, Student s1) {
		if (s1 == null) {
			return null;
		}

		// try to find the foreign key
		Optional<Student> stdTemp = studentRepository.findById(s1.getId());

		if (!stdTemp.isPresent()) {
			return null;
		}

		// assign the corresponding user info
		s1.setUserInfo(stdTemp.get().getUserInfo());

		// update the password
		s1.getUserInfo().setPassword(pwd);

		s1.getUserInfo().setRole("Student");

		Student sRes = null;
		try {

			sRes = studentRepository.saveAndFlush(s1);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sRes;
	}

	@Override
	public boolean DeleteUserAndStudent(int stdId) {

		boolean bRes = false;

		// try to find the foreign key
		Optional<Student> stdTemp = studentRepository.findById(stdId);

		if (!stdTemp.isPresent()) {
			return bRes;
		}

		try {
			studentRepository.delete(stdTemp.get());
			bRes = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return bRes;
	}

}
