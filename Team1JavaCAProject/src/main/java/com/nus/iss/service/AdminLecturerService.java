package com.nus.iss.service;

import java.util.ArrayList;
import java.util.List;

import com.nus.iss.exception.MyRepositoryException;
import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Lecturer;

public interface AdminLecturerService {

	ArrayList<Lecturer> findAllLecturers();

	List<Lecturer> FindAll();

	Lecturer FindById(int lecId);

	Lecturer CreateUserAndLecturer(CapsUser u1, Lecturer l1) throws MyRepositoryException;

	Lecturer UpdateUserAndLecturer(String pwd, Lecturer l1) throws MyRepositoryException;

	boolean DeleteUserAndLecturer(int lecId) throws MyRepositoryException;

}
