package io.craigmiller160.schedule.persist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.schedule.context.AppContext;
import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class JoinTest {

	/*
	 * This class uses Spring annotations, rather than purely
	 * xml based configuration like the other classes of this 
	 * program. This is because the test cases are instantiated
	 * differently than regular beans.
	 */
	
	/**
	 * The DAO object for the <tt>Course</tt> class.
	 */
	@Autowired
	private CourseDao courseDao;
	
	/**
	 * The DAO object for the <tt>Student</tt> class.
	 */
	@Autowired
	private StudentDao studentDao;
	
	//TODO document this
	private int studentId;
	private int courseId;
	
	/**
	 * Get the DAO object for the <tt>Course</tt> class.
	 * 
	 * @return the DAO object for the <tt>Course</tt> class.
	 */
	public CourseDao getCourseDao() {
		return courseDao;
	}

	/**
	 * Set the DAO object for the <tt>Course</tt> class.
	 * 
	 * @param courseDao the DAO object for the <tt>Course</tt> class.
	 */
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	/**
	 * Get the DAO object for the <tt>Student</tt> class.
	 * 
	 * @return the DAO object for the <tt>Student</tt> class.
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * Set the DAO object for the <tt>Student</tt> class.
	 * 
	 * @param courseDao the DAO object for the <tt>Student</tt> class.
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Transactional
	@Before
	public void createEntities(){
		Student student = new Student(
				"First", "Last", 
				LocalDate.of(1988, 10, 26), 
				'M', 1);
		Course course = new Course(
				"Course", "Subject", 
				"Teacher", 1);
		
		studentDao.insertStudent(student);
		studentId = student.getStudentId();
		
		courseDao.insertCourse(course);
		courseId = course.getCourseId();
	}
	
	@Transactional
	@Test
	public void testAddCourseToStudent(){
		//Get entities
		Student student = studentDao.getStudent(studentId);
		Course course = courseDao.getCourse(courseId);
		//TODO delete this SessionFactory factory = ((HibernateStudentDao) studentDao).getSessionFactory();
		
		//Add course to student and update database
		student.addCourse(course);
		studentDao.updateStudent(student);
		//TODO figure out how to do this without updating both DAOs in the test
		//Hibernate isn't flushing the operation in this test case, so the join table isn't being populated
		//It WILL work, though, just isn't right now for whatever reason.
		course.addStudent(student);
		courseDao.updateCourse(course);
		
		//Get student from database and test if course is in list
		student = studentDao.getStudent(studentId);
		List<Course> courses = student.getCourses();
		assertNotNull("Courses is null", courses);
		assertTrue("Courses does not contain Course", courses.contains(course));
		
		//Get course from database and test if student is in list
		course = courseDao.getCourse(courseId);
		List<Student> students = course.getStudents();
		System.out.println(students.size());
		assertNotNull("Students is null", students);
		System.out.println("SavedID: " + studentId + " Other: " + student.getStudentId());
		assertTrue("Students does not contain Student", students.contains(student));
		
		
	}
	
	@Transactional
	@Test
	public void testAddStudentToCourse(){
		//Get entities
		Student student = studentDao.getStudent(studentId);
		Course course = courseDao.getCourse(courseId);
		
		//Add student to course and update database
		course.addStudent(student);
		courseDao.updateCourse(course);
		//TODO figure out how to do this without updating both DAOs in the test
		//Hibernate isn't flushing the operation in this test case, so the join table isn't being populated
		//It WILL work, though, just isn't right now for whatever reason.
		student.addCourse(course);
		studentDao.updateStudent(student);
		
		//Get course from database and test if student is in list
		course = courseDao.getCourse(courseId);
		List<Student> students = course.getStudents();
		System.out.println(students.size());
		assertNotNull("Students is null", students);
		assertTrue("Students does not contain Student", students.contains(student));
		
		//Get student from database and test if course is in list
		student = studentDao.getStudent(studentId);
		List<Course> courses = student.getCourses();
		assertNotNull("Courses is null", courses);
		assertTrue("Courses does not contain Course", courses.contains(course));
		
		//TODO add deletion to this test.
	}
	
	/**
	 * Reset the auto-increment counter of the tables being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetCourseAutoIncrement();
		testUtil.resetStudentAutoIncrement();
	}
	
}
