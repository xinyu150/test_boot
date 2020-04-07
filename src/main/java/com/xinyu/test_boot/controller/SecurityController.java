package com.xinyu.test_boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/security")
public class SecurityController {

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String getHello(){
        return "hello";
    }

}
