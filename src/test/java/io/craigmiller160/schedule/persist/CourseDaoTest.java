package io.craigmiller160.schedule.persist;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.schedule.entity.Course;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class CourseDaoTest extends TestCase{
	
	private static final String INSERT_FAIL = "Insert Failed";
	private static final String UPDATE_FAIL = "Update Failed";
	private static final String DELETE_FAIL = "Delete Failed";
	
	@Autowired
	private CourseDao courseDao;

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	@Test
	@Transactional
	public void testCourseOperations(){
		Course course = new Course();
		setCourse1(course);
		courseDao.insertCourse(course);
		int courseId = course.getCourseId();
		
		course = courseDao.getCourse(courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(INSERT_FAIL, course.getCourseName(), "Name");
		assertEquals(INSERT_FAIL, course.getSubject(), "Subject");
		assertEquals(INSERT_FAIL, course.getTeacherLastName(), "LastName");
		assertEquals(INSERT_FAIL, course.getPeriod(), 1);
		
		setCourse2(course);
		courseDao.updateCourse(course);
		
		course = courseDao.getCourse(courseId);
		assertNotNull(UPDATE_FAIL, course);
		assertEquals(UPDATE_FAIL, course.getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, course.getSubject(), "Subject2");
		assertEquals(UPDATE_FAIL, course.getTeacherLastName(), "LastName2");
		assertEquals(UPDATE_FAIL, course.getPeriod(), 2);
		
		courseDao.deleteCourse(course);
		
		course = courseDao.getCourse(courseId);
		assertNull(DELETE_FAIL, course);
	}
	
	private void setCourse1(Course course){
		course.setCourseName("Name");
		course.setSubject("Subject");
		course.setTeacherLastName("LastName");
		course.setPeriod(1);
	}
	
	private void setCourse2(Course course){
		course.setCourseName("Name2");
		course.setSubject("Subject2");
		course.setTeacherLastName("LastName2");
		course.setPeriod(2);
	}
	
	@Test
	@Transactional
	public void testListOperation(){
		Course course = new Course();
		setCourse1(course);
		courseDao.insertCourse(course);
		
		List<Course> courses = courseDao.getAllCourses();
		assertNotNull("Courses list is null", courses);
		assertTrue("Courses list less than 1", courses.size() >= 1);
		assertTrue("Courses list doesn't contain course", courses.contains(course));
	}
	
}
