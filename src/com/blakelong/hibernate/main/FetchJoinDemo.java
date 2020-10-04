package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from the db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			System.out.println("breakpoint here Instructor: " + instructor);
			
			System.out.println("breakpoint as Courses: " + instructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\n**The session is closed here**\n");
			// option 1: call getter while session is open
			
			// retrieve the courses for instructor after session is closed by first 
			// lazy loading courses when session was still open
			System.out.println("breakpoint as Courses: " + instructor.getCourses());
			
			System.out.println("Done");
			
		} finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
