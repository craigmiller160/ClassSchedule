package io.craigmiller160.schedule.persist;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;

/**
 * Default implementation of the <tt>ScheduleService</tt> class.
 * This class defines the service layer of the Class Schedule
 * program. It uses the DAOs for the <tt>Student</tt> and <tt>Course</tt>
 * classes for the basic CRUD operations they provide, and performs
 * additional operations on top of them. It also provides handles
 * starting, committing, and - if necessary - rolling back all transactions.
 * <p>
 * <b>THREAD SAFE:<b> This class is thread-safe.
 * It has no mutable state that could cause issues
 * with multiple threads.
 * 
 * @author craig
 * @version 1.0
 */
public class ScheduleServiceImpl implements ScheduleService {

	/**
	 * The DAO for persisting <tt>Student</tt> objects.
	 */
	private final StudentDao studentDao;
	
	/**
	 * The DAO for persisting <tt>Course</tt> objects.
	 */
	private final CourseDao courseDao;
	
	/**
	 * Create a new instance of this service, setting
	 * both DAOs that it requires. If null is passed as 
	 * either DAO parameter, this class will not be able
	 * to function.
	 * 
	 * @param studentDao the DAO for persisting <tt>Student</tt>
	 * objects.
	 * @param courseDao the DAO for persisting <tt>Course</tt>
	 * objects.
	 */
	public ScheduleServiceImpl(StudentDao studentDao, CourseDao courseDao){
		this.studentDao = studentDao;
		this.courseDao = courseDao;
	}
	
	/**
	 * Get the <tt>StudentDao</tt> object for this class.  
	 * 
	 * @return the <tt>StudentDao</tt> object for this class.
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * Get the <tt>CourseDao</tt> object for this class.  
	 * 
	 * @return the <tt>CourseDao</tt> object for this class.
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	public CourseDao getCourseDao() {
		return courseDao;
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
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

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public void saveStudent(Student student) {
		studentDao.updateStudent(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public void deleteStudent(Student student) {
		studentDao.deleteStudent(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public Student getStudent(int studentId) {
		return studentDao.getStudent(studentId);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
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

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public void saveCourse(Course course) {
		courseDao.updateCourse(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if a DAO was set
	 * to null. 
	 */
	@Transactional
	@Override
	public Course getCourse(int courseId) {
		return courseDao.getCourse(courseId);
	}
	
	/**
	 * Reset the auto-increment counter for both DAOs. This will set the counter
	 * generating ids to the next highest number based on the
	 * records currently in the table. This is especially useful
	 * during testing operations.
	 * <p>
	 * <b>NOTE:</b> This is NOT a mandatory operation for either DAO class.
	 * This method tests both DAO implementations to see if they support this
	 * method. If either DAO does not support this method, an exception will
	 * be thrown.
	 * 
	 * @throws NullPointerException if a DAO was set
	 * to null.
	 * @throws UnsupportedOperationException if this operation is
	 * not supported by either DAO implementation in this class.
	 */
	public void resetAutoIncrement(){
		if(studentDao instanceof HibernateStudentDao 
				&& courseDao instanceof HibernateCourseDao){
			((HibernateStudentDao) studentDao).resetAutoIncrement();
			((HibernateCourseDao) courseDao).resetAutoIncrement();
		}
		else{
			throw new UnsupportedOperationException(
					"Only Hibernate DAO classes supported");
		}
	}

}
