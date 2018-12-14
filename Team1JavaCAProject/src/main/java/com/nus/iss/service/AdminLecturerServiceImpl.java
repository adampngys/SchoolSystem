package com.nus.iss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nus.iss.exception.MyRepositoryException;
import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Lecturer;
import com.nus.iss.repository.LecturerRepository;
import com.nus.iss.repository.UserRepository;

@Service
public class AdminLecturerServiceImpl implements AdminLecturerService {
	@Resource
	private LecturerRepository lecturerRepository;

	@Resource
	private UserRepository userRepository;

	@Override
	public ArrayList<Lecturer> findAllLecturers() {
		return (ArrayList<Lecturer>) lecturerRepository.findAll();
	}

	@Override
	public List<Lecturer> FindAll() {
		List<Lecturer> lecList = null;
		try {
			lecList = lecturerRepository.findAll();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lecList;
	}

	@Override
	public Lecturer FindById(int lecId) {
		Lecturer lec = null;
		try {
			lec = lecturerRepository.findById(lecId).get(); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lec;
	}

	@Override
	public Lecturer CreateUserAndLecturer(CapsUser u1, Lecturer l1) throws MyRepositoryException {
		if (u1 == null || l1 == null)
			return null;

		if (u1.getId() == null)
			return null;

		// check the existed user, if already exist then return null
		if (userRepository.findById(u1.getId()).isPresent()) {
			return null;
		}
		u1.setRole("Lecturer");

		// add foreign key info to student table
		l1.setUserInfo(u1);

		Lecturer lRes = null;
		try {

			lRes = lecturerRepository.saveAndFlush(l1);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new MyRepositoryException("CreateUserAndLecturer error");
		}
		return lRes;
	}

	@Override
	public Lecturer UpdateUserAndLecturer(String pwd, Lecturer l1) throws MyRepositoryException {
		if (l1 == null) {
			return null;
		}

		// try to find the foreign key
		Optional<Lecturer> lecTemp = lecturerRepository.findById(l1.getLectId());

		if (!lecTemp.isPresent()) {
			return null;
		}

		// assign the corresponding user info
		l1.setUserInfo(lecTemp.get().getUserInfo());

		// update the password
		l1.getUserInfo().setPassword(pwd);

		l1.getUserInfo().setRole("lecturer");

		Lecturer lRes = null;
		try {

			lRes = lecturerRepository.saveAndFlush(l1);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new MyRepositoryException("UpdateUserAndLecturer error");
		}
		return lRes;
	}

	@Override
	public boolean DeleteUserAndLecturer(int lecId) throws MyRepositoryException {

		boolean bRes = false;

		// try to find the foreign key
		Optional<Lecturer> lecTemp = lecturerRepository.findById(lecId);

		if (!lecTemp.isPresent()) {
			return bRes;
		}

		try {
			lecturerRepository.delete(lecTemp.get());
			bRes = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new MyRepositoryException("DeleteUserAndLecturer error");
		}
		return bRes;
	}

}
