package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes=HibernateApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseSpringDataRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void findById_CoursePresent(){
		//returns optional
		Optional<Course> courseOptional = repository.findById(1001L);
		logger.info("{}", courseOptional.isPresent()); //true
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent(){
		//returns optional
		Optional<Course> courseOptional = repository.findById(2001L);
		logger.info("{}", courseOptional.isPresent()); //false
		assertFalse(courseOptional.isPresent());
	}

	@Test
	public void saving_updating(){
		Course c = new Course("Microservices in 100 Steps");
		repository.save(c);
		c.setName("Microservices in 100 Steps- Updated");
		repository.save(c);
	}

	@Test
	public void findAll(){
		logger.info("Courses -> {}",repository.findAll());
	}

	@Test
	public void count(){
		logger.info("count -> {}",repository.count());
	}

//	@Test
//	public void sort(){
//		Sort sort = new Sort(Sort.Direction.DESC, "name");
//		logger.info("sorted courses -> {}",repository.findAll(sort));
//	}

	@Test
	public void pagination(){
		PageRequest pageRequest = PageRequest.of(0,3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {}" ,firstPage.getContent());

		Pageable second = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(second);
		logger.info("Second Page -> {}" ,secondPage.getContent());
	}

	@Test
	public void findUsingName() {
		logger.info("FindByName -> {}",repository.findByName("JPA in 50 Steps"));
	}




}
