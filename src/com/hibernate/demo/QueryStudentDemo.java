package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
								
			//START A TRANSACTION
			session.beginTransaction();
			
			//QUERY STUDENTS
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//DISPLAY THE STUDENTS
			displayStudents(theStudents);
			
			//QUERY STUDENTS : lastName='Carvajal'
			theStudents = session.createQuery("from Student s where s.lastName='Carvajal'").getResultList();
			
			//DISPLAY THE STUDENTS
			System.out.println("\n\nStudent who last name of Carvajal");
			displayStudents(theStudents);
			
			//QUERY STUDENTS : lastName='Carvajal' OR firstName='Victor'
			theStudents = session.createQuery("from Student s where s.lastName='Carvajal' OR s.firstName='Victor'").getResultList();
			
			//DISPLAY THE STUDENTS
			System.out.println("\n\nStudent who last name of Carvajal or first name Victor");
			displayStudents(theStudents);
			
			//QUERY STUDENTS : WHERE EMAIL LIKE %GMAIL.COM
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			
			//DISPLAY THE STUDENTS
			System.out.println("\n\nStudent who last gmail emails");
			displayStudents(theStudents);
			
			//COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
