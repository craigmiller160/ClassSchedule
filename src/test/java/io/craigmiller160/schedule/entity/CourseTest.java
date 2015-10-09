package io.craigmiller160.schedule.entity;

import java.util.TreeSet;

import junit.framework.TestCase;

public class CourseTest extends TestCase {

	public void testFields(){
		Course course = new Course();
		assertNotNull(course);
		course.setCourseId(1);
		course.setCourseName("Algebra");
		course.setSubject("Math");
		course.setTeacherLastName("Flubber");
		course.setPeriod(5);
		
		assertEquals(course.getCourseId(), 1);
		assertEquals(course.getCourseName(), "Algebra");
		assertEquals(course.getSubject(), "Math");
		assertEquals(course.getTeacherLastName(), "Flubber");
		assertEquals(course.getPeriod(), 5);
	}
	
	public void testEquals(){
		Object o = new Object();
		
		Course course1 = new Course();
		course1.setCourseId(1);
		
		Course course2 = new Course();
		course2.setCourseId(2);
		
		Course course3 = new Course();
		course3.setCourseId(1);
		
		assertFalse(course1.equals(course2));
		assertFalse(course1.equals(o));
		assertTrue(course1.equals(course3));
	}
	
	public void testCompareTo(){
		Course course1 = new Course();
		course1.setCourseId(1);
		
		Course course2 = new Course();
		course2.setCourseId(2);
		
		Course course3 = new Course();
		course3.setCourseId(3);
		
		TreeSet<Course> set = new TreeSet<>();
		set.add(course2);
		set.add(course1);
		set.add(course3);
		
		assertEquals(set.size(), 3);
		assertEquals(set.pollFirst(), course1);
		assertEquals(set.pollFirst(), course2);
		assertEquals(set.pollFirst(), course3);
	}
	
}
