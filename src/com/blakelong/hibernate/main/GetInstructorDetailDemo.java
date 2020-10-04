package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
		
		// get the instructor detail object
		int instructorDetailId = 1;
		
		InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
		
		// print the instructor detail
		System.out.println("instructorDetail: " + instructorDetail);
		
		// print the associated instructor
		System.out.println("The associated instructor: " + instructorDetail.getInstructor());
		
		// commit transaction
		session.getTransaction().commit();
		
		System.out.println("Done");
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// handle connection leak 
			session.close();
			
			factory.close();
		}
	}
}
