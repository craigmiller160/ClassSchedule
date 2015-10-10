package io.craigmiller160.schedule.persist;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.schedule.entity.Student;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"classpath:/test-context.xml"})
public class StudentDaoTest extends TestCase{

	private static final String INSERT_FAIL = "Insert Failed";
	private static final String UPDATE_FAIL = "Update Failed";
	private static final String DELETE_FAIL = "Delete Failed";
	
	//TODO document how this class has the Spring dependencies
	@Autowired
	private StudentDao studentDao;
	
	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Test
	@Transactional
	public void testStudentOperations(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		int studentId = student.getStudentId();
		
		student = studentDao.getStudent(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
		assertEquals(INSERT_FAIL, student.getLastName(), "Last");
		assertEquals(INSERT_FAIL, student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, student.getGender(), 'U');
		assertEquals(INSERT_FAIL, student.getGrade(), 1);
		
		setStudent2(student);
		studentDao.updateStudent(student);
		
		student = studentDao.getStudent(studentId);
		assertNotNull(UPDATE_FAIL, student);
		assertEquals(UPDATE_FAIL, student.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, student.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, student.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, student.getGender(), 'M');
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		studentDao.deleteStudent(student);
		
		student = studentDao.getStudent(studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	private void setStudent1(Student student){
		student.setFirstName("First");
		student.setLastName("Last");
		student.setBirthDate(LocalDate.of(1900, 1, 1));
		student.setGender('U');
		student.setGrade(1);
	}
	
	private void setStudent2(Student student){
		student.setFirstName("First2");
		student.setLastName("Last2");
		student.setBirthDate(LocalDate.of(1950, 1, 1));
		student.setGender('M');
		student.setGrade(2);
	}
	
	@Test
	@Transactional
	public void testListOperation(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		
		List<Student> students = studentDao.getAllStudents();
		assertNotNull("Students list is null", students);
		assertTrue("Students list less than 1", students.size() >= 1);
		assertTrue("Students list doesn't contain student", students.contains(student));
	}
	
}
