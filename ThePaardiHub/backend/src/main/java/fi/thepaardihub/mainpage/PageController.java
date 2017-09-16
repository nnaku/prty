package fi.thepaardihub.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView( "public/index.html");
	}
	
	


}
