package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/api")
public class BasicController {
    
    @GetMapping("/")
    String hello (){
        return "index.html";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String HelloController(){

        return "Hello, 리액트 백단";
    }

}
