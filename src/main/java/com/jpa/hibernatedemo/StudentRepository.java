package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class,id);
    }
    public void deleteById(Long id){
        Student c = findById(id);
        em.remove(c);
    }

    public Student save(Student student){
        if(student.getId()==null){
            //insert
            em.persist(student);
        }else{
            //update
            em.merge(student);
        }
        return student;
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

//        Course c = new Course("Web Services in 100 Steps");
//        em.persist(c);
//        Course c2 = findById(1001L);
//        c2.setName("JPA in 50 Steps - Updated");
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("Z1");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertStudentAndCourseHardcoded(){
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 steps");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertStudentAndCourse(Student student,Course course){
        student.addCourse(course);
        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }


}
