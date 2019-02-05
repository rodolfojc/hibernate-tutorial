package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
								
			//CREATE A STUDENT OBJECT
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Victor", "Rangel", "victorangel@gmail.com");		
			
			//START A TRANSACTION
			session.beginTransaction();
			
			//SAVE THE STUDENT OBJECT
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			//NEW CODE
			
			//FIND OUT THE STUDENT'S ID: PRIMARY KEY
			System.out.println("Save student. Generated id: "+tempStudent.getId());
			
			//GET A NEW SESSION AND START TRANSACTION
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//RETREIVE STUDENT BASED ON THE ID/PK
			System.out.println("\nGetting student id: "+tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: "+myStudent);
			
			//COMMIT THE TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
