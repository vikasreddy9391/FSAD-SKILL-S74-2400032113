package com.klu.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Academic Year: ");
        int year = sc.nextInt();

        System.out.println("\n--- Using XML Configuration ---");

        ApplicationContext xmlContext =
                new ClassPathXmlApplicationContext("applicationcontext.xml");

        Student student1 = new Student(id, name, course, year);
        student1.display();

        System.out.println("\n--- Using Annotation Configuration ---");

        ApplicationContext annContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student student2 = new Student(id, name, course, year);
        student2.display();

        sc.close();
    }
}
