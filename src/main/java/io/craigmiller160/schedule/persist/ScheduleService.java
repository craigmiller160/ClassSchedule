package io.craigmiller160.schedule.persist;

import java.time.LocalDate;
import java.util.List;

import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;

public interface ScheduleService {

	Student createStudent(String firstName, String lastName, 
			LocalDate birthDate, char gender, int grade);
	
	void saveStudent(Student student);
	
	void deleteStudent(Student student);
	
	List<Student> getAllStudents();
	
	Student getStudent(int studentId);
	
	Course createCourse(String courseName, String teacherLastName, 
			String subject, int period);
	
	void saveCourse(Course course);
	
	void deleteCourse(Course course);
	
	List<Course> getAllCourses();
	
	Course getCourse(int courseId);
	
}
