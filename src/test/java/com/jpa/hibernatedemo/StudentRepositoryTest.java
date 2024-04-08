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
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails(){
        Student s = em.find(Student.class,2001);
        logger.info("student->{}",s);
        logger.info("passport -> {}",s.getPassport()); //org.hibernate.LazyInitializationException
    }

    @Test
    @Transactional
    //org.hibernate.LazyInitializationException: could not initialize proxy [com.jpa.hibernatedemo.Passport#4001] - no Session
    public void someTest() {
        //EntityManager & Transaction & Persistence Context & Session & SessionFactory
        Student s = em.find(Student.class,2001L);

        Passport p = s.getPassport();

        p.setNumber("E12");

        s.setName("Ranga - Updated");
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent(){
        Passport p = em.find(Passport.class,4001L);
        logger.info("Passport->{}",p);
        logger.info("Student -> {}",p.getStudent());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = em.find(Student.class,2001L);
        logger.info("student-{}",student);
        logger.info("courses -> {}",student.getCourses());
    }

    @Test
    @Transactional
    public void setAddress_Details_for_Student(){
        Student student = em.find(Student.class,2001L);
        student.setAddress(new Address("No 101","Some Street","Hyderabad"));
        em.flush();
    }


}
