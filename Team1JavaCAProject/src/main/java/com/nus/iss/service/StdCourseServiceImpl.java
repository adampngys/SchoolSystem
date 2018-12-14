package com.nus.iss.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nus.iss.model.StdCourse;
import com.nus.iss.repository.StdCourseRepository;

@Service
public class StdCourseServiceImpl implements StdCourseService {

	@Resource
	private StdCourseRepository stdCourseRepository;

	@Override
	@Transactional
	public StdCourse addCourse(StdCourse sc) {
		StdCourse sss = null;
		try {
			sss = stdCourseRepository.saveAndFlush(sc);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sss;
	}

	@Override
	@Transactional
	public ArrayList<StdCourse> listStudentCourse(int sid) {
		// TODO Auto-generated method stub
		return stdCourseRepository.findStdCourseByStdId(sid);
	}

	@Override
	@Transactional
	public StdCourse findStdCourse(int StdCourseId) {
		// TODO Auto-generated method stub
		System.out.println("StdCourse Id: " + StdCourseId);

		StdCourse stdCourse = stdCourseRepository.findById(StdCourseId).get();
		System.out.println(stdCourse.toString());
		return stdCourse;
	}

	@Override
	public void removeStdCourse(StdCourse sc) {
		// TODO Auto-generated method stub
		try {
			stdCourseRepository.delete(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
