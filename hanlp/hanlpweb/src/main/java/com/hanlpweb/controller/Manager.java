package com.hanlpweb.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class Manager {
    
    private static Logger logger = Logger.getLogger(Manager.class);

    @RequestMapping(value={"/index"}, method=RequestMethod.GET)
    public String managerShow(){
        return "welcome";
    }
}

