package io.craigmiller160.schedule.persist;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.craigmiller160.schedule.entity.Course;
import io.craigmiller160.schedule.entity.Student;
import junit.framework.TestCase;

//TODO this class should be documented how the database should
//always be checked if it fails, because otherwise dummy data
//could persist
public class ScheduleServiceTest extends TestCase {

	private ScheduleService service;
	private ApplicationContext context;
	
	private int studentId;
	private int altStudentId;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private char gender;
	private int grade;
	private int courseId;
	private int altCourseId;
	private String courseName;
	private String teacherLastName;
	private String subject;
	private int period;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext("data-context.xml");
		service = context.getBean(ScheduleService.class, "scheduleService");
		
		firstName = "Joe";
		lastName = "Dirt";
		birthDate = LocalDate.of(1988, 10, 26);
		gender = 'M';
		grade = 10;
		
		Student student = service.createStudent(firstName, lastName, birthDate, gender, grade);
		studentId = student.getStudentId();
		
		courseName = "Algebra";
		teacherLastName = "Iron Man";
		subject = "Math";
		period = 5;
		
		Course course = service.createCourse(courseName, teacherLastName, subject, period);
		courseId = course.getCourseId();
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		((AbstractApplicationContext) context).close();
		//TODO tearDown should run the delete methods no matter what
	}
	
	public void studentTest(){
		//Testing insert
		Student student = service.getStudent(studentId);
		studentAssertions(student);
		
		//Changing values
		firstName = "John";
		student.setFirstName(firstName);
		lastName = "Doe";
		student.setLastName(lastName);
		birthDate = LocalDate.of(1990, 1, 1);
		student.setBirthDate(birthDate);
		gender = 'U';
		student.setGender(gender);
		grade = 3;
		student.setGrade(grade);
		
		//Saving entity
		service.saveStudent(student);
		
		//Testing update
		student = service.getStudent(studentId);
		studentAssertions(student);
	}
	
	private void studentAssertions(Student student){
		assertNotNull(student);
		assertEquals(student.getFirstName(), firstName);
		assertEquals(student.getLastName(), lastName);
		assertEquals(student.getBirthDate(), birthDate);
		assertEquals(student.getGender(), gender);
		assertEquals(student.getGrade(), grade);
	}
	
	public void courseTest(){
		//Testing insert
		Course course = service.getCourse(courseId);
		courseAssertions(course);
		
		//Changing values
		courseName = "Chemistry";
		course.setCourseName(courseName);
		teacherLastName = "Flubber";
		course.setTeacherLastName(teacherLastName);
		subject = "Science";
		course.setSubject(subject);
		period = 1;
		course.setPeriod(period);
		
		//Saving entity
		service.saveCourse(course);
		
		//Testing update
		course = service.getCourse(courseId);
		courseAssertions(course);
	}
	
	private void courseAssertions(Course course){
		assertNotNull(course);
		assertEquals(course.getCourseName(), courseName);
		assertEquals(course.getTeacherLastName(), teacherLastName);
		assertEquals(course.getSubject(), subject);
		assertEquals(course.getPeriod(), period);
	}
	
	public void addCourseToStudentTest(){
		
	}
}
