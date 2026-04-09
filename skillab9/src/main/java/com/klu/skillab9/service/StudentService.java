package com.klu.skillab9.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.skillab9.exception.StudentNotFoundException;
import com.klu.skillab9.model.Student;
import com.klu.skillab9.repository.StudentRepository;


@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student getStudentById(int id) {

        Optional<Student> student = repository.findById(id);

        if(student.isEmpty()) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }

        return student.get();
    }

    // ADD THIS METHOD
    public Student saveStudent(Student student) {
        return repository.save(student);
    }
}