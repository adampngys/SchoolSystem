package com.nus.iss.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Lecturer;
import com.nus.iss.model.Student;

enum roleEnum {
	student, lecturer, admin
}

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSession {

	private CapsUser user;
	private roleEnum role;
	private String sessionId = null;
	private Student student = null;
	private Lecturer lecturer = null;

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public CapsUser getUser() {
		return user;
	}

	public void setUser(CapsUser user) {
		this.user = user;

		if (user != null) {
			// set the role info
			if (user.getLec() != null) {
				this.role = roleEnum.lecturer;
			} else if (user.getStd() != null) {
				this.role = roleEnum.student;
			} else {
				this.role = roleEnum.admin;
			}
		} else {
			this.role = null;
		}
	}

	public roleEnum getRole() {
		return role;
	}

	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}