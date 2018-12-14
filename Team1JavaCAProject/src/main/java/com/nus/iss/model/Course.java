package com.nus.iss.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "course")

public class Course {
	
	@Id
	@Column(name = "course_id_pk")
	private String courseId;
	
	@Basic
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "startdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Basic
	@Column(name="duration")
	private int duration;
	
	@Basic
	@Column(name="size")
	private int size;
	
	@Basic
	@Column(name="credit")
	private int credit;
	
	@Basic
	@Column(name="vacancy")
	private int vacancy;
	
	@ManyToMany(mappedBy="courses")
	private Collection<Student> students;
	
	public Collection<Student> getStudents() {
		return students;
	}


	public void setStudents(Collection<Student> students) {
		this.students = students;
	}


	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Course(String courseId, String name, Date startDate, int duration, int size, int credit, int vacancy) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
		this.size = size;
		this.credit = credit;
		this.vacancy = vacancy;
	}


	public Course(String courseId) {
		super();
		this.courseId = courseId;
	}


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getVacancy() {
		return vacancy;
	}


	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}


	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + credit;
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + size;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + vacancy;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (credit != other.credit)
			return false;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (vacancy != other.vacancy)
			return false;
		return true;
	}

	
	

}

