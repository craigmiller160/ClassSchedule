package io.craigmiller160.schedule.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import io.craigmiller160.schedule.persist.ScheduleService;

public class LoginController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ScheduleService service = getApplicationContext().getBean(ScheduleService.class, "scheduleService");
		
		
		
		return null;
	}

}
