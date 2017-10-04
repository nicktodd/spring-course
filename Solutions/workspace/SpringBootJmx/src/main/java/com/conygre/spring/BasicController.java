package com.conygre.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nicktodd on 12/10/2016.
 */
@RestController
@RequestMapping("/example")
@EnableAutoConfiguration
@ComponentScan
public class BasicController {

    @ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    public String sayHello() {
        jmxComponent.incrementNumberOfGetRequests();
        return "hello from the controller";
    }

    @Autowired
    private JmxComponentImpl jmxComponent;




    public static void main(String[] args) throws Exception {
        SpringApplication.run(BasicController.class, args);
    }


}
