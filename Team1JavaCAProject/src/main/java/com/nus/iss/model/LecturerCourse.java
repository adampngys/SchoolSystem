package com.nus.iss.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lecturer_course")

public class LecturerCourse {
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "lect_id_pk")
	private Lecturer lecturer;

	@ManyToOne
	@JoinColumn(name = "course_id_pk")
	private Course course;

	public Date getEndDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(course.getStartDate());
		cal.add(cal.DAY_OF_MONTH, course.getDuration());
		Date endDate = cal.getTime();
		// DateTimeFormatter tt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		LocalDateTime time = (LocalDateTime) endDate;
		return endDate;
	}

	LecturerCourse() {

	}

	public LecturerCourse(int id, Lecturer lecturer, Course course) {
		super();
		this.id = id;
		this.lecturer = lecturer;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "LecturerCourse [id=" + id + ", lecturer=" + lecturer.getName() + ", course=" + course.getName() + "]";
	}

}
