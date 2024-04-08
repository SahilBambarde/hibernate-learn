package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes=HibernateApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void findAll_basic() {
//        TypedQuery<Course> query = em.createQuery("Select c From Course c",Course.class);
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}",resultList);
    }
    @Test
    void findByWhere_basic() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c where name like '%100 Steps' -> {}",resultList);
    }

    @Test
    void courses_without_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}",resultList);
    }

    @Test
    void courses_with_atleast_2_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students)>=2",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}",resultList);
    }

    @Test
    void courses_ordered_by_students_asending() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}",resultList);
    }

    @Test
    void courses_ordered_by_students_desending() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc",Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}",resultList);
    }

    @Test
    void students_with_passport_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like 'E1'",Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}",resultList);
    }

    //like
    //BETWEEN 100 and 1000
    //IS NULL
    //upper , lower, trim, length


    //join => Select c , s from Course c JOIN c.students s
    //left join => Select c, s from Course c LEFT JOIN c.students s
    //cross join => Select c, s from Course c, Students s
    @Test
    public void join(){
        Query query = em.createQuery("Select c , s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}",resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}",result[0],result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query = em.createQuery("Select c , s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}",resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}",result[0],result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size-> {}",resultList.size());
        for(Object[] result:resultList){
            logger.info("Course{} Student{}",result[0],result[1]);
        }
    }
}
