package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private long id;
    private String name;
    private String gender;

    @Autowired
    private Certification certification;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Certification getCertification() {
        return certification;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Certification ID: " + certification.getId());
        System.out.println("Certification Name: " + certification.getName());
        System.out.println("Date of Completion: " + certification.getDateOfCompletion());
    }
}
