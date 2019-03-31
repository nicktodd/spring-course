package com.conygre.training.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class HelloWorldController {

    @RequestMapping(method= RequestMethod.GET)
    public String sayHello() {
        return "hello";
    }

}
