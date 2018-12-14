package com.nus.iss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name="user")
@Scope("session")
public class CapsUser {
	
	@Id
	@Column(name="user_id_pk")
	private String id;	
	private String password;
	private String role;
	
	@OneToOne(mappedBy = "userInfo")
	private Student std;
	
	@OneToOne(mappedBy = "userInfo")
	private Lecturer lec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Student getStd() {
		return std;
	}

	public void setStd(Student std) {
		this.std = std;
	}

	public Lecturer getLec() {
		return lec;
	}

	public void setLec(Lecturer lec) {
		this.lec = lec;
	}
	
}
