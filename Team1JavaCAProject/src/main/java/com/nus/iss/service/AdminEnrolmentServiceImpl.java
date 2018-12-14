package com.nus.iss.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nus.iss.model.Course;
import com.nus.iss.model.StdCourse;
import com.nus.iss.repository.CourseRepository;
import com.nus.iss.repository.StdCourseRepository;

@Service
public class AdminEnrolmentServiceImpl implements AdminEnrolmentService {

	@Resource
	private CourseRepository courseRepository;

	@Resource
	private StdCourseRepository studentcourseRepository;

	/*
	 * public ArrayList<Student> findAllStudents(){ ArrayList<Student> uList =
	 * (ArrayList<Student>) studentcourseRepository.findAll(); return uList;
	 * 
	 * }
	 */
	@Override
	@Transactional
	public ArrayList<Course> findAllCourse() {
		ArrayList<Course> uList = (ArrayList<Course>) courseRepository.findAll();
		return uList;

	}

	@Override
	@Transactional
	public ArrayList<StdCourse> findStudentCourse() {

		ArrayList<StdCourse> lcList = (ArrayList<StdCourse>) studentcourseRepository.findAll();

		return lcList;
	}

	@Override
	public boolean enrolmentApprove(int StdCourseId) {

		boolean bRes = false;
		Optional<StdCourse> sc1 = studentcourseRepository.findById(StdCourseId);
		if (!sc1.isPresent()) {
			return bRes;
		}

		sc1.get().setStatus("approve");
		sc1.get().getCourse().setVacancy(sc1.get().getCourse().getVacancy() - 1);

		try {
			studentcourseRepository.saveAndFlush(sc1.get());
			bRes = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return bRes;
	}

	@Override
	public boolean enrolmentReject(int StdCourseId) {
		boolean bRes = false;
		Optional<StdCourse> sc1 = studentcourseRepository.findById(StdCourseId);
		if (!sc1.isPresent()) {
			return bRes;
		}

		sc1.get().setStatus("reject");

		try {
			studentcourseRepository.saveAndFlush(sc1.get());
			bRes = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return bRes;
	}

	@Override
	public ArrayList<StdCourse> findAllApply() {

		ArrayList<StdCourse> res = null;
		try {
			res = (ArrayList<StdCourse>) studentcourseRepository.findAllWithStatus("in-progress");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return res;
	}

}
