package io.craigmiller160.schedule.persist;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;

import io.craigmiller160.schedule.entity.Course;

/**
 * An implementation of <tt>CourseDao</tt> using the
 * <tt>Hibernate</tt> framework. This class depends
 * on a <tt>Hibernate SessionFactory</tt> to generate
 * the database sessions & connections. This class
 * does NOT manage its own transactions, it will
 * depend on a service layer class to handle that 
 * functionality.
 * <p>
 * <b>THREAD SAFETY:</b> This class is thread-safe.
 * It has no mutable state that could cause issues
 * with multiple threads.
 * 
 * @author craig
 * @version 1.0
 */
public class HibernateCourseDao implements CourseDao {

	/**
	 * The <tt>SessionFactory</tt> that this class uses
	 * for connecting to the database.
	 */
	private final SessionFactory sessionFactory;
	
	/**
	 * Create this DAO with the mandatory <tt>SessionFactory</tt>
	 * that it requires. Passing null as this parameter will
	 * cause this class to not be able to function.
	 * 
	 * @param sessionFactory the <tt>SessionFactory</tt> this class
	 * needs to create database sessions.
	 */
	public HibernateCourseDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get the <tt>SessionFactory</tt> used by this class
	 * for database sessions.
	 * 
	 * @return the <tt>SessionFactory used by this class.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void insertCourse(Course course) {
		sessionFactory.getCurrentSession().save(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void updateCourse(Course course) {
		sessionFactory.getCurrentSession().update(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public Course getCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		return (Course) session.createCriteria(Course.class)
					.setFetchMode("students", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("courseId", courseId))
					.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Criteria.list() doesn't support generics
	@Override
	public List<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Course.class)
				.list();
	}
	
	@Override
	public List<Course> getCoursesInRange(int startIndex, int endIndex){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("coursesByIndexRangeProcedure")
				.setInteger("startIndex", startIndex)
				.setInteger("endIndex", endIndex);
		return query.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void deleteCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
	}
	
	//TODO remove this
	/**
	 * Reset the auto-increment counter on the database table
	 * for the <tt>Course</tt> class. This will set the counter
	 * generating ids to the next highest number based on the
	 * records currently in the table. This is especially useful
	 * during testing operations.
	 * 
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public void resetAutoIncrement(){
		Dialect dialect = Dialect.getDialect();
		if(dialect instanceof MySQLDialect){
			sessionFactory.getCurrentSession()
			.createQuery("alter table course auto_increment = 1")
			.executeUpdate();
		}
		else{
			throw new UnsupportedOperationException(
					"Method is only compatible with MySQL database");
		}
	}
	
	/**
	 * Close the <tt>SessionFactory</tt> when this class's work
	 * is complete.
	 * 
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public void closeSessionFactory(){
		sessionFactory.close();
	}

	@Override
	public long getCourseCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from Course").uniqueResult();
	}

}
