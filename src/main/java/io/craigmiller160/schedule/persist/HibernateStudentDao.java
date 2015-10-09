package io.craigmiller160.schedule.persist;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import io.craigmiller160.schedule.entity.Student;

public class HibernateStudentDao implements StudentDao {

	private final SessionFactory sessionFactory;
	
	public HibernateStudentDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void insertStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	@Override
	public void updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}

	@Override
	public Student getStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		return (Student) session.createCriteria(Student.class)
					.setFetchMode("courses", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("studentId", studentId))
					.uniqueResult();
	}

	@SuppressWarnings("unchecked") //Criteria.list() doesn't support generics
	@Override
	public List<Student> getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Student.class)
				.list();
	}

	@Override
	public void deleteStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);
	}
	
	public void resetAutoIncrement(){
		sessionFactory.getCurrentSession()
			.createQuery("alter table student auto_increment = 1")
			.executeUpdate();
	}

}
