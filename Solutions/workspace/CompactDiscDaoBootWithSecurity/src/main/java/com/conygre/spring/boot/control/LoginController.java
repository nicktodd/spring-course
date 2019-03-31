package com.conygre.spring.boot.control;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nicktodd on 29/07/2016.
 */
@Controller
public class LoginController {
    @RequestMapping(method = RequestMethod.GET, value="/login")
    public String login() {
        return "login";
    }


}
