package io.craigmiller160.schedule.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="course")
public class Course implements Comparable<Course>{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="course_id")
	private int courseId;
	
	private String subject;
	
	@Column (name="course_name")
	private String courseName;
	
	@Column (name="teacher_last_name")
	private String teacherLastName;
	
	private int period;
	
	@ManyToMany (cascade=CascadeType.ALL, mappedBy="courses") //TODO not sure if cascading is appropriate here or not
	private List<Student> students;
	//TODO might want to change this to be a set... debating this
	
	public Course() {}
	
	public Course(String courseName, String teacherLastName, int period){
		this.courseName = courseName;
		this.teacherLastName = teacherLastName;
		this.period = period;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getTeacherLastName() {
		return teacherLastName;
	}
	
	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public List<Student> getStudents(){
		return students;
	}
	
	public void setStudents(List<Student> students){
		this.students = students;
	}
	
	public boolean addStudent(Student student){
		student.addCourse(this);
		return students.add(student);
	}
	
	public boolean removeStudent(Student student){
		
		return students.remove(student);
	}
	
	@Override
	public String toString(){
		return courseName;
	}
	
	@Override
	public int hashCode(){
		return courseId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Course){
			return ((Course) obj).courseId == this.courseId;
		}
		else{
			return false;
		}
	}

	@Override
	public int compareTo(Course course) {
		return ((Integer) this.courseId)
				.compareTo((Integer) course.courseId);
	}
	
}
