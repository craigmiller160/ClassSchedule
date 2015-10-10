package io.craigmiller160.schedule.persist;

import java.time.LocalDate;
import java.util.List;

import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;

/**
 * Interface for the service layer for the Class Schedule program. It
 * performs additional operations on top of the basic
 * CRUD operations of the DAOs. 
 * 
 * @author craig
 * @version 1.0
 */
public interface ScheduleService {

	/**
	 * Create and persist a new student based on 
	 * the provided parameters.
	 * 
	 * @param firstName the student's first name.
	 * @param lastName the student's last name.
	 * @param birthDate the student's birth date.
	 * @param gender the student's gender.
	 * @param grade the student's grade.
	 * @return the created student.
	 */
	Student createStudent(String firstName, String lastName, 
			LocalDate birthDate, char gender, int grade);
	
	/**
	 * Save the changes to a student.
	 * 
	 * @param student the student to save changes to.
	 */
	void saveStudent(Student student);
	
	/**
	 * Delete a persisted student.
	 * 
	 * @param student the student to delete.
	 */
	void deleteStudent(Student student);
	
	/**
	 * Get a list of all persisted students.
	 * 
	 * @return a list of all persisted students.
	 */
	List<Student> getAllStudents();
	
	/**
	 * Get a persisted student, specified by the id.
	 * 
	 * @param studentId the id of the student.
	 * @return the persisted student.
	 */
	Student getStudent(int studentId);
	
	/**
	 * Create and persist a new course based on 
	 * the provided parameters. 
	 * 
	 * @param courseName the name of the course.
	 * @param teacherLastName the last name of the teacher of the course.
	 * @param subject the subject of the course. 
	 * @param period the period the course is taught.
	 * @return the created course.
	 */
	Course createCourse(String courseName, String teacherLastName, 
			String subject, int period);
	
	/**
	 * Save the changes to a course.
	 * 
	 * @param course the course to save changes to.
	 */
	void saveCourse(Course course);
	
	/**
	 * Delete a persisted course.
	 * 
	 * @param course the course to delete.
	 */
	void deleteCourse(Course course);
	
	/**
	 * Get a list of all persisted courses.
	 * 
	 * @return a list of all persisted courses.
	 */
	List<Course> getAllCourses();
	
	/**
	 * Get a persisted course, specified by the id.
	 * 
	 * @param courseId the id of the course.
	 * @return the persisted course.
	 */
	Course getCourse(int courseId);
	
}
