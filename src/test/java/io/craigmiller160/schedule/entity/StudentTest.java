package io.craigmiller160.schedule.entity;

import java.time.LocalDate;
import java.util.TreeSet;

import junit.framework.TestCase;

public class StudentTest extends TestCase {

	public void testFields(){
		Student student = new Student();
		assertNotNull(student);
		student.setStudentId(1);
		student.setFirstName("Joe");
		student.setLastName("Dirt");
		student.setBirthDate(LocalDate.of(1988, 10, 26));
		student.setGender('M');
		student.setGrade(5);
		
		assertEquals(student.getStudentId(), 1);
		assertEquals(student.getFirstName(), "Joe");
		assertEquals(student.getLastName(), "Dirt");
		assertEquals(student.getBirthDate(), LocalDate.of(1988, 10, 26));
		assertEquals(student.getGender(), 'M');
		assertEquals(student.getGrade(), 5);
	}
	
	public void testEquals(){
		Object o = new Object();
		
		Student student1 = new Student();
		student1.setStudentId(1);
		
		Student student2 = new Student();
		student2.setStudentId(2);
		
		Student student3 = new Student();
		student3.setStudentId(1);
		
		assertFalse(student1.equals(student2));
		assertFalse(student1.equals(o));
		assertTrue(student1.equals(student3));
	}
	
	public void testCompareTo(){
		Student student1 = new Student();
		student1.setStudentId(1);
		
		Student student2 = new Student();
		student2.setStudentId(2);
		
		Student student3 = new Student();
		student3.setStudentId(3);
		
		TreeSet<Student> set = new TreeSet<>();
		set.add(student2);
		set.add(student1);
		set.add(student3);
		
		assertEquals(set.size(), 3);
		assertEquals(set.pollFirst(), student1);
		assertEquals(set.pollFirst(), student2);
		assertEquals(set.pollFirst(), student3);
	}
	
}
