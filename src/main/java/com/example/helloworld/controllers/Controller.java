package com.example.helloworld.controllers;

import com.example.helloworld.configurations.Configuration;
import com.example.helloworld.entity.Student;
import com.example.helloworld.entity.Teacher;
import com.example.helloworld.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/report")
public class Controller {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Configuration configuration;

    @Autowired
    GenerateReportServiceImpl generateReportService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping("/pdf/{id}")
    public String pdfReport(@PathVariable("id") Integer id) {
        return generateReportService.exportPdf(id);
    }

    @GetMapping("/txt")
    public String txtReport() {
        return generateReportService.exportTxt();
    }

    @GetMapping("/csv")
    public String csvReport() {
        return generateReportService.exportCsv();
    }

    @GetMapping("/xlsx")
    public String xlsxReport() {
      return   generateReportService.exportXlsx();
    }




    @GetMapping("/getall")
    public List<Student> getallstudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public List<Student> getteacher(@PathVariable("id") Integer id) {
        return studentRepository.findByTeacherId(id);
    }



    @GetMapping("/hello/{name}")
    public String sayhello(@PathVariable("name") String name) {
        System.out.println(name);
        configuration.setName(name);
        name = configuration.getName();
        return "hello " + name;
    }

}
