package com.jpa.hibernatedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository repository;

	@Autowired
	private StudentRepository studentrepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Course course = repository.findById(1001L);
//		logger.info("Course 1001 ->{}",course);
//		repository.deleteById(1003L);
//		repository.save(new Course("Microservices in 100 Steps"));

//		repository.playWithEntityManager();
		//Studentrepository.saveStudentWithPassport();
		//repository.addReviewForCourseHardCoded();

//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hands-on Stuff."));
//		reviews.add(new Review("5","HatsOff."));
//		repository.addReviewsForCourse(1003L,reviews);

//		studentrepository.insertStudentAndCourse(new Student("Jack"),new Course("Microservices in 100 Steps"));

//		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal(10000)));
//		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal(50)));
//		logger.info("PartTime Employees -> {}",employeeRepository.retrieveAllPartTimeEmployees());
//		logger.info("FullTime Employees -> {}",employeeRepository.retrieveAllFullTimeEmployees());
	}
}
