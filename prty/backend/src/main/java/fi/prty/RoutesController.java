package fi.prty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class RoutesController {

	/**
	 * Handles vue routes.
	 * @return redirect to index.html
	 */
@RequestMapping({
	"/home",
	"/about",
	"/user",
	"/games",
	"/login",
	"/logout",
	"/reset",
	"/register",
	"/test",
	"/lobby",
	"/game"
	})
   public String index() {
       return "forward:/index.html";
   }
}