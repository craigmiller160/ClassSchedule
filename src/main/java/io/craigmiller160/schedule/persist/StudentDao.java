package io.craigmiller160.schedule.persist;

import java.util.List;

import io.craigmiller160.schedule.entity.Student;

public interface StudentDao {

	void insertStudent(Student student);
	
	void updateStudent(Student student);
	
	Student getStudent(int studentId);
	
	List<Student> getAllStudents();
	
	void deleteStudent(Student student);
	
}
