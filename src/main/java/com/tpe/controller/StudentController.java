package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/students") // students endpointi ile gelen requestler icin bu sinifa bakilacagini belirtiyorum.
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hi") // students/hi
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Merhaba");
        mav.addObject("messagebody","Ben bir ogrenci yonetim sistemiyim");
        mav.setViewName("hi"); // hi.jsp
        return mav;
    }

    @GetMapping("/new") // students/new
    public String sendStudentForm(@ModelAttribute("student")Student student){
        return "studentForm"; // @ModelAttribute= Model e student isminde bir attribute ekledik
    }

    @PostMapping("/saveStudent") // students/saveStudent
    public String createStudent(@ModelAttribute Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping
    public ModelAndView getStudents(){
        List<Student> list= studentService.getAllStudent();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("students",list);
        modelAndView.setViewName("students");
        return modelAndView;
    }

    // http://localhost:8080/springmvc/students/update?id=1
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Student student= studentService.findStudentById(id);
        model.addAttribute(student);
        return "studentForm"; // studentForm.jsp sayfasini client tarafina gonderiyorum.
    }

    // http://localhost:8080/springmvc/students/delete/1
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
