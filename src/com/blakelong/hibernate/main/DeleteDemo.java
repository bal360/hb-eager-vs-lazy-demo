package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		
		try {
		// start a transaction
		session.beginTransaction();	
		
		// get instructor by primary key - id
		int instructorId = 2;
		Instructor instructor = session.get(Instructor.class, instructorId);
		
		System.out.println("Found instructor: " + instructor);
		
		// delete instructor AND InstructorDetail object
		// because of CascaseType.ALL
		if (instructor != null) {
			session.delete(instructor);
		}
		
		// commit transaction
		session.getTransaction().commit();
		System.out.println("Done");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
