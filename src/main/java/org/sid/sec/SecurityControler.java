package org.sid.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityControler {
	
@RequestMapping(value = "/login")

public String login() {
    	return "login";
    }


@RequestMapping(value = "/", method = RequestMethod.GET)

public String home() {
	return "redirect:/operations";
}
@RequestMapping("/403")
public String accessDined(){

    return "403";
}
}
