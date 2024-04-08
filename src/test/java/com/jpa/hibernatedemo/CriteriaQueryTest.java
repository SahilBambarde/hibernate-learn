package com.jpa.hibernatedemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes=HibernateApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void findAll_basic() {
        //Select c From Course c
        //1. Use criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. define roots for tables which are invloved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. define predicates etc using criteria builder
        //4. add predicates etc to criteria query
        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}",resultList);
    }

    @Test
    void all_course_having_100_Steps_basic() {
        //Select c From Course c where name like '%100 Steps'
        //1. Use criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. define roots for tables which are invloved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. define predicates etc using criteria builder
        Predicate like = cb.like(courseRoot.get("name"),"%100 Steps");

        //4. add predicates etc to criteria query
        cq.where(like);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}",resultList);
    }

    @Test
    void all_course_without_students() {
        //Select c From Course c where c.students is empty
        //1. Use criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. define roots for tables which are invloved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. define predicates etc using criteria builder
        Predicate empty = cb.isEmpty(courseRoot.get("students"));

        //4. add predicates etc to criteria query
        cq.where(empty);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}",resultList);
    }

    @Test
    void join() {
        //Select c From Course c join c.students s
        //1. Use criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. define roots for tables which are invloved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. define predicates etc using criteria builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. add predicates etc to criteria query


        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}",resultList);
    }

    @Test
    void left_join() {
        //Select c From Course c join c.students s
        //1. Use criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. define roots for tables which are invloved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. define predicates etc using criteria builder
        Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);

        //4. add predicates etc to criteria query


        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}",resultList);
    }
}
