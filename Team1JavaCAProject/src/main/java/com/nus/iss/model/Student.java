package com.nus.iss.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name ="native", strategy = "native")
	@Column(name = "std_id_pk")
	private int id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "NRIC")
	private String nric;

	@Basic
	@Column(name = "address")
	private String address;

	@Basic
	@Column(name = "phone")
	private String phone;

	@Basic
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private Date dob;

	@Basic
	@Column(name = "email")
	private String email;

	@Basic
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "enrollment_date")
	private Date enrollmentDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk")
	private CapsUser userInfo;
	

	public CapsUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(CapsUser userInfo) {
		this.userInfo = userInfo;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	@ManyToMany
	@JoinTable(name = "std_course", joinColumns = @JoinColumn(name = "std_id_fk"), inverseJoinColumns = @JoinColumn(name = "course_id_fk"))
	private Collection<Course> courses;

	/*
	 * @OneToMany
	 * 
	 * @JoinTable(name = "std_course", joinColumns = { @JoinColumn(name =
	 * "std_id_pk") }, inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "course_id_pk") }) private Collection<Course> courses;
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}


	public Student(int id, String name, String nric, String address, String phone, Date dob, String email,
			Date enrollmentDate, CapsUser userInfo) {
		super();
		this.id = id;
		this.name = name;
		this.nric = nric;
		this.address = address;
		this.phone = phone;
		this.dob = dob;
		this.email = email;
		this.enrollmentDate = enrollmentDate;
		this.userInfo = userInfo;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
