package io.craigmiller160.schedule.persist;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import io.craigmiller160.schedule.entity.Course;

public class HibernateCourseDao implements CourseDao {

	private final SessionFactory sessionFactory;
	
	public HibernateCourseDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertCourse(Course course) {
		sessionFactory.getCurrentSession().save(course);
	}

	@Override
	public void updateCourse(Course course) {
		sessionFactory.getCurrentSession().update(course);
	}

	@Override
	public Course getCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		return (Course) session.createCriteria(Course.class)
					.setFetchMode("students", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("courseId", courseId))
					.uniqueResult();
	}

	@SuppressWarnings("unchecked") //Criteria.list() doesn't support generics
	@Override
	public List<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Course.class)
				.list();
	}

	@Override
	public void deleteCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
	}

}
