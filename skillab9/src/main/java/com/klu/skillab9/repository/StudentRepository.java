package com.klu.skillab9.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.skillab9.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}