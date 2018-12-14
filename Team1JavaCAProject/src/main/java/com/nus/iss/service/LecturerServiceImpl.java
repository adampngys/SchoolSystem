package com.nus.iss.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nus.iss.model.Lecturer;
import com.nus.iss.model.LecturerCourse;
import com.nus.iss.model.StdCourse;
import com.nus.iss.repository.LecturerCourseRepository;
import com.nus.iss.repository.LecturerRepository;
import com.nus.iss.repository.StdCourseRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Resource
	private LecturerCourseRepository lecturerCourseRepository;

	@Resource
	private StdCourseRepository studentCourseRepository;

	@Resource
	private LecturerRepository lecturerRepository;

	@Override
	@Transactional
	public ArrayList<LecturerCourse> findAllCoursesByLID(Integer lid) {

		return lecturerCourseRepository.findCoursesByLID(lid);
	}

	@Override
	@Transactional
	public double getGPA(int score) {
		double gpa = 0;
		if (score < 85 && score >= 40) {

			int b;
			b = (score - 35) / 5;
			gpa = b * 0.5 + 0.5;
		}
		if (score >= 85)
			gpa = 5.0;
		if (score < 40)
			gpa = 0;

		return gpa;
	}

	@Override
	@Transactional
	public String getGrade(Double gpa) {
		String grade = null;
		if (gpa == 5)
			grade = "A+";
		if (gpa == 4.5)
			grade = "A";
		if (gpa == 4)
			grade = "A-";
		if (gpa == 3.5)
			grade = "B+";
		if (gpa == 3)
			grade = "B";
		if (gpa == 2.5)
			grade = "B-";
		if (gpa == 2)
			grade = "C+";
		if (gpa == 1.5)
			grade = "C";
		if (gpa == 1)
			grade = "D";

		if (gpa == 0)
			grade = "F";

		return grade;
	}

	@Override
	@Transactional
	public StdCourse updategrade(StdCourse stdcourse) {
		return studentCourseRepository.saveAndFlush(stdcourse);
	}

	@Override
	@Transactional
	public ArrayList<StdCourse> findAllStudentsByCID(String cid) {
		return studentCourseRepository.findStudentsByCID(cid);
	}

	@Override
	@Transactional
	public StdCourse findStdCourse(Integer id) {
		return studentCourseRepository.findOne(id);

	}

	@Override
	@Transactional
	public Lecturer findByLecturerId(int lid) {

		return lecturerRepository.findByLecturerId(lid);
	}

}
