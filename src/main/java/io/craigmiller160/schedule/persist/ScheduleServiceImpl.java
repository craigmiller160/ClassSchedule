package io.craigmiller160.schedule.persist;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;

public class ScheduleServiceImpl implements ScheduleService {

	private final StudentDao studentDao;
	private final CourseDao courseDao;
	
	public ScheduleServiceImpl(StudentDao studentDao, CourseDao courseDao){
		this.studentDao = studentDao;
		this.courseDao = courseDao;
	}
	
	public StudentDao getStudentDao() {
		return studentDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	@Transactional
	@Override
	public Student createStudent(String firstName, String lastName, 
			LocalDate birthDate, char gender, int grade) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setBirthDate(birthDate);
		student.setGender(gender);
		student.setGrade(grade);
		studentDao.insertStudent(student);
		
		return student;
	}

	@Transactional
	@Override
	public void saveStudent(Student student) {
		studentDao.updateStudent(student);
	}

	@Transactional
	@Override
	public void deleteStudent(Student student) {
		studentDao.deleteStudent(student);
	}

	@Transactional
	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Transactional
	@Override
	public Student getStudent(int studentId) {
		return studentDao.getStudent(studentId);
	}

	@Transactional
	@Override
	public Course createCourse(String courseName, String teacherLastName, 
			String subject, int period) {
		Course course = new Course();
		course.setCourseName(courseName);
		course.setTeacherLastName(teacherLastName);
		course.setSubject(subject);
		course.setPeriod(period);
		courseDao.insertCourse(course);
		
		return course;
	}

	@Transactional
	@Override
	public void saveCourse(Course course) {
		courseDao.updateCourse(course);
	}

	@Transactional
	@Override
	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

	@Transactional
	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Transactional
	@Override
	public Course getCourse(int courseId) {
		return courseDao.getCourse(courseId);
	}
	
	public void resetAutoIncrement(){
		
	}

}
