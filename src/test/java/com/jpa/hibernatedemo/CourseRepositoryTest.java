package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes=HibernateApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	void findById_basic() {
		Course course = repository.findById(1001L);
		assertEquals("JPA in 50 Steps",course.getName());
	}

	@Test
	void findById_firstLevelCacheDemo() {
		Course course = repository.findById(1001L);
		logger.info("First Course Retrieved {}",course);

		Course course1 = repository.findById(1001L);
		logger.info("First Course Retrieved again {}",course1);

		assertEquals("JPA in 50 Steps",course.getName());
		assertEquals("JPA in 50 Steps",course1.getName());
	}
	@Test
	@DirtiesContext
	void deleteById_basic() {
		repository.deleteById(1002L);
		assertNull(repository.findById(1002L));
	}

	@Test
	void save_basic_update(){
		Course c = repository.findById(1001L);
		c.setName("JPA in 50 Steps - Updated");
		repository.save(c);
		Course c1 =repository.findById(1001L);
		assertEquals("JPA in 50 Steps - Updated",c1.getName());
	}

	@Test
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void retreiveReviewsForCourse() {
		Course course = repository.findById(1001L);
		logger.info("{}",course.getReviews());
	}

	@Test
	@Transactional
	public void retreiveCourseForReview() {
		Review review = em.find(Review.class,5001L);
		logger.info("{}",review.getCourse());
	}




}
