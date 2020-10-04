package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		// create factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create objects
			Instructor tempInstructor = 
					new Instructor("Susan", "Studebaker", "susie@email.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.susan.com/youtube", "coloring");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// *** this will ALSO save the InstructorDetail object ***
			// because of CascadeType.ALL
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
