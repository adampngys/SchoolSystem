package com.nus.iss.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "std_course")
public class StdCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private int id;

	@Basic
	@Column(name = "GPA")
	private Double gpa;

	@Basic
	@Column(name = "grade")
	private String grade;

	@Basic
	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "std_id_fk")
	private Student student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id_fk")
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getGpa() {
		return gpa;
	}

	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public StdCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StdCourse(Double gpa, String grade, String status, Student student, Course course) {
		super();
		this.gpa = gpa;
		this.grade = grade;
		this.status = status;
		this.student = student;
		this.course = course;
	}

	public StdCourse(Double gpa, String grade, Student student, Course course) {
		super();
		this.gpa = gpa;
		this.grade = grade;
		this.student = student;
		this.course = course;
	}

	public StdCourse(Double gpa, String grade) {
		super();
		this.gpa = gpa;
		this.grade = grade;
	}

	public StdCourse(String status, Course course) {
		super();
		this.status = status;
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((gpa == null) ? 0 : gpa.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		StdCourse other = (StdCourse) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (gpa == null) {
			if (other.gpa != null)
				return false;
		} else if (!gpa.equals(other.gpa))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StdCourse [id=" + id + ", gpa=" + gpa + ", grade=" + grade + ", status=" + status + ", student="
				+ student + ", course=" + course + "]";
	}

}