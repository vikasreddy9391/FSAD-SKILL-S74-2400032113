package com.example.courseapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.courseapi.entity.Course;
import com.example.courseapi.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

 @Autowired
 private CourseService service;

 @PostMapping
 public ResponseEntity<?> addCourse(@RequestBody Course course){
  return new ResponseEntity<>(service.addCourse(course),HttpStatus.CREATED);
 }

 @GetMapping
 public ResponseEntity<?> getCourses(){
  return new ResponseEntity<>(service.getCourses(),HttpStatus.OK);
 }

 @PutMapping("/{id}")
 public ResponseEntity<?> updateCourse(@PathVariable int id,@RequestBody Course course){
  Course updated = service.updateCourse(id,course);
  if(updated!=null)
   return new ResponseEntity<>(updated,HttpStatus.OK);
  else
   return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteCourse(@PathVariable int id){
  boolean deleted = service.deleteCourse(id);
  if(deleted)
   return new ResponseEntity<>("Course Deleted",HttpStatus.OK);
  else
   return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
 }

 @GetMapping("/search/{title}")
 public ResponseEntity<?> search(@PathVariable String title){
  List<Course> list = service.searchByTitle(title);
  if(list.isEmpty())
   return new ResponseEntity<>("No Course Found",HttpStatus.NOT_FOUND);
  else
   return new ResponseEntity<>(list,HttpStatus.OK);
 }

}