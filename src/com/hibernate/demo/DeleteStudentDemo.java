package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			//FIRST WAY
			//DELETE THE STUDENT OBJECT
			System.out.println("Deleting student: "+studenId);
			session.delete(myStudent);
			
			//SECOND WAY
			//DELETE THE STUDENT ID=2
			System.out.println("Deleting student id 2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//COMMIT THE TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
