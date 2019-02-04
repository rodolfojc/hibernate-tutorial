package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
	
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
										
			//CREATE 3 STUDENT OBJECT
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Juan", "Velesquez", "juanvz@gmail.com");		
			Student tempStudent2 = new Student("Carlos", "Velar", "carlosvelar@gmail.com");
			Student tempStudent3 = new Student("Cesar", "Padron", "cesarparque@gmail.com");
			
			//START A TRANSACTION
			session.beginTransaction();
				
			//SAVE THE STUDENT OBJECT
			System.out.println("Saving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
					
			//COMMIT TRANSACTION
			session.getTransaction().commit();
					
			System.out.println("Done!");
					
			}
			finally {
				factory.close();
			}

	}

}
