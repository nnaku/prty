package fi.thepaardihub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RoutesController {

@RequestMapping({
	"/home",
	"/about",
	"/user",
	"/games",
	"/login",
	"/",
	"/",
	"/",
	"/",
	"/",
	"/"
	})
   public String index() {
       return "forward:/index.html";
   }
}