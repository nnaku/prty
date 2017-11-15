package fi.thepaardihub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class ThePaardiHub {

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("/public/index.html");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ThePaardiHub.class, args);
	}
	
}
