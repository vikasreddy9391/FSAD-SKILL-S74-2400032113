package com.example.courseapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.courseapi.entity.Course;
import com.example.courseapi.repository.CourseRepository;

@Service
public class CourseService {

 @Autowired
 private CourseRepository repo;

 public Course addCourse(Course course){
  return repo.save(course);
 }

 public List<Course> getCourses(){
  return repo.findAll();
 }

 public Course updateCourse(int id, Course course){
  Course c = repo.findById(id).orElse(null);
  if(c!=null){
   c.setTitle(course.getTitle());
   c.setDuration(course.getDuration());
   c.setFee(course.getFee());
   return repo.save(c);
  }
  return null;
 }

 public boolean deleteCourse(int id){
  if(repo.existsById(id)){
   repo.deleteById(id);
   return true;
  }
  return false;
 }

 public List<Course> searchByTitle(String title){
  return repo.findByTitle(title);
 }

}