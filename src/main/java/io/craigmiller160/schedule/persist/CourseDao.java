package io.craigmiller160.schedule.persist;

import java.util.List;

import io.craigmiller160.schedule.entity.Course;

public interface CourseDao {

	void insertCourse(Course course);
	
	void updateCourse(Course course);
	
	Course getCourse(int courseId);
	
	List<Course> getAllCourses();
	
	void deleteCourse(Course course);
	
}
