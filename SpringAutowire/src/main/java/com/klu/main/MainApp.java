package com.klu.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;
import com.klu.model.Certification;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean(Student.class);
        Certification cert = student.getCertification();

        System.out.print("Enter Student ID: ");
        student.setId(sc.nextLong());

        sc.nextLine();
        System.out.print("Enter Student Name: ");
        student.setName(sc.nextLine());

        System.out.print("Enter Gender: ");
        student.setGender(sc.nextLine());

        System.out.print("Enter Certification ID: ");
        cert.setId(sc.nextLine());

        System.out.print("Enter Certification Name: ");
        cert.setName(sc.nextLine());

        System.out.print("Enter Date of Completion: ");
        cert.setDateOfCompletion(sc.nextLine());

        System.out.println();

        student.display();

        sc.close();
    }
}
