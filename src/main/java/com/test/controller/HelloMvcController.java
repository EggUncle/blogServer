package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by egguncle on 17-1-10.
 */

@Controller
@RequestMapping("/hello")
public class HelloMvcController {


    @RequestMapping("/mvc")
    //响应 host:8080/hello/mvc
    public String helloMVC(){
        return "home";
    }


}
