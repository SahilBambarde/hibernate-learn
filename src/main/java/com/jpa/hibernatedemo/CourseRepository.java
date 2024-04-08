package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseRepository{
    @Autowired
    EntityManager em;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public Course findById(Long id){
        Course c = em.find(Course.class,id);
        logger.info("First Course Retrived {}", c);
        return c;
    }
    public void deleteById(Long id){
        Course c = findById(id);
        em.remove(c);
    }

    public Course save(Course course){
        if(course.getId()==null){
            //insert
            em.persist(course);
        }else{
            //update
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {

//        em.persist(c);
//        Course c2 = new Course("AngularJS in 100 Steps");
//        em.persist(c2);
//
//        em.flush();
//
////        em.clear();
////        em.detach(c);
////        em.detach(c2);
//
//        c.setName("Web Services in 100 Steps - Updated");
//        c2.setName("AngularJS in 100 Steps-Updated");
//
//        em.refresh(c);


//        Course c = new Course("Web Services in 100 Steps");
//        c.setName(null);
//        em.persist(c);
//        em.flush();

        Course c = new Course("Web Services in 100 Steps");
        em.persist(c);
        Course c2 = findById(1001L);
        c2.setName("JPA in 50 Steps - Updated");
    }

    public void addReviewForCourseHardCoded() {
        Course c = findById(1003L);
        logger.info("course.getReview() -> {}",c.getReviews());
        Review r1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");
        Review r2 = new Review(ReviewRating.FIVE,"HatsOff.");
        c.addReview(r1);
        r1.setCourse(c);
        c.addReview(r2);
        r2.setCourse(c);
        em.persist(r1);
        em.persist(r2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews){
        Course c = findById(courseId);
        logger.info("course.getReview() -> {}",c.getReviews());
        for(Review review: reviews){
            c.addReview(review);
            review.setCourse(c);
            em.persist(review);
        }
    }
}
