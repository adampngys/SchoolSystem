package com.nus.iss.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lecturer")
public class Lecturer {

	@Id
	@Column(name = "lect_id_pk")
	private int lectId;

	private String name;
	private String faculty;
	private String phone;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String email;

	@OneToMany
	@JoinTable(name = "lecturer_course", 
joinColumns = { @JoinColumn(name = "lect_id_pk") }, 
inverseJoinColumns = {	@JoinColumn(name = "course_id_pk") })
	private Collection<Course> courses;	
	
	public int getLectId() {
		return lectId;
	}

	public void setLectId(int lectId) {
		this.lectId = lectId;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk")
	private CapsUser userInfo;
	

	public CapsUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(CapsUser userInfo) {
		this.userInfo = userInfo;
	}
	
	public Lecturer() {

	}

	public Lecturer(int lect_id_pk, String name, String faculty, String phone, Date dob, String email,
			CapsUser userInfo) {
		super();
		this.lectId = lect_id_pk;
		this.name = name;
		this.faculty = faculty;
		this.phone = phone;
		this.dob = dob;
		this.email = email;
		this.userInfo = userInfo;
	}

	public int getLect_id_pk() {
		return lectId;
	}

	public void setLect_id_pk(int lect_id_pk) {
		this.lectId = lect_id_pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Lecturer [lect_id_pk=" + lectId + ", name=" + name + ", faculty=" + faculty + ", phone=" + phone
				+ ", dob=" + dob + ", email=" + email + "]";
	}

}
