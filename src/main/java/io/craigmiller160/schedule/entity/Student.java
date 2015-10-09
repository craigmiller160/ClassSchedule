package io.craigmiller160.schedule.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.craigmiller160.schedule.util.LocalDateConverter;

@Entity
@Table (name="student")
public class Student implements Comparable<Student>{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="student_id")
	private int studentId;
	
	@Column (name="first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;
	
	@Column (name="birth_date")
	@Convert (converter=LocalDateConverter.class)
	private LocalDate birthDate;
	
	private char gender;
	
	private int grade;
	
	@ManyToMany () //TODO not sure if cascading is appropriate here or not
	@JoinTable (name="schedule_join", 
				joinColumns={@JoinColumn (name="student_id")}, 
				inverseJoinColumns={@JoinColumn (name="course_id")})
	private List<Course> courses = new ArrayList<>();
	//TODO might want to change this to be a set... debating this
	
	public Student() {}
	
	public Student(String firstName, String lastName, LocalDate birthDate, int grade){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.grade = grade;
	}
	
	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public boolean addCourse(Course course){
		course.addStudent(this);
		return courses.add(course);
	}
	
	//TODO need to have a remove operation reflected on both sides
	public boolean removeCourse(Course course){
		return courses.remove(course);
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}
	
	@Override
	public int hashCode(){
		return studentId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Student){
			return ((Student) obj).studentId == this.studentId;
		}
		else{
			return false;
		}
	}

	@Override
	public int compareTo(Student student) {
		return ((Integer) this.studentId)
				.compareTo((Integer) student.studentId);
	}
	
}