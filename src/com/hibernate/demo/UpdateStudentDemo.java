package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
								
			int studenId = 1;
					
			//GET A NEW SESSION AND START TRANSACTION
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//RETREIVE STUDENT BASED ON THE ID/PK
			System.out.println("\nGetting student id: "+studenId);
			
			Student myStudent = session.get(Student.class, studenId);
			
			//SETTING NEW VALUE
			System.out.println("Updating student...");
			myStudent.setFirstName("John");
			
			//COMMIT THE TRANSACTION
			session.getTransaction().commit();
			
			//NEW CODE
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//UPDATE EMAIL ADDRESS FOR ALL STUDENTS 
			System.out.println("Updating email for all student...");
			session.createQuery("update Student set email='example@example.com'").executeUpdate();
			
			//COMMIT THE TRANSACTION
			session.getTransaction().commit();
		
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
