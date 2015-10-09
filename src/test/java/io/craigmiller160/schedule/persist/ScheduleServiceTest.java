package io.craigmiller160.schedule.persist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class ScheduleServiceTest extends TestCase {

	private ScheduleService service;
	private ApplicationContext context;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext("data-context.xml");
		service = context.getBean(ScheduleService.class, "scheduleService");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		((AbstractApplicationContext) context).close();
	}
	
	
}
