package com.tpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students") // students endpointi ile gelen requestler icin bu sinifa bakilacagini belirtiyorum.
public class StudentController {

    @GetMapping("/hi") // students/hi
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Merhaba");
        mav.addObject("messagebody","Ben bir ogrenci yonetim sistemiyim");
        mav.setViewName("hi"); // hi.jsp
        return mav;
    }
}
