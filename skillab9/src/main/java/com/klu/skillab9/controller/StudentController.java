package com.klu.skillab9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.skillab9.model.Student;
import com.klu.skillab9.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    // GET API
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudentById(id);
    }

    // POST API
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }
}