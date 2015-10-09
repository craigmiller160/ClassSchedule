import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.craigmiller160.schedule.entity.Student;
import io.craigmiller160.schedule.persist.ScheduleService;

public class Tester {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("data-context.xml");
		ScheduleService service = context.getBean(ScheduleService.class, "scheduleService");
		
		Student s = service.createStudent("John", "Doe", LocalDate.of(1988, 10, 26), 'M', 10);
		System.out.println(s);
	}

}
