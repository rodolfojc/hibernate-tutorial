package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			Student tempStudent = new Student("Rodolfo", "Carvajal", "rodolfojcarvajalm@gmail.com");		
			
			//START A TRANSACTION
			session.beginTransaction();
			
			//SAVE THE STUDENT OBJECT
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			//COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
