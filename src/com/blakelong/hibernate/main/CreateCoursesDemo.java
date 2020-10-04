package com.blakelong.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.blakelong.hibernate.entity.Course;
import com.blakelong.hibernate.entity.Instructor;
import com.blakelong.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			// create courses
			Course course1 = new Course("Air Guitar - The Ultimate Guide");
			Course course2 = new Course("The Pinball Masterclass");
			
			// add courses to instructor
			instructor.add(course1);
			instructor.add(course2);
			
			// save the courses
			session.save(course1);
			session.save(course2);
			
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
