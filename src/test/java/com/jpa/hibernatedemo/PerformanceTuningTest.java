package com.jpa.hibernatedemo;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes=HibernateApplication.class)
class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void CreateNPlusOneProblem() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses",Course.class).
                getResultList();
        for(Course course:courses){
            logger.info("Course -> {} Student -> {}",course,course.getStudents());
        }

    }

    @Test
    @Transactional
    void SolvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph =em.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
        List<Course> courses = em.createNamedQuery("query_get_all_courses",Course.class).
                setHint("javac.persistence.loadgraph",entityGraph).
                getResultList();
        for(Course course:courses){
            logger.info("Course -> {} Student -> {}",course,course.getStudents());
        }

    }

    @Test
    @Transactional
    void CreateNPlusOneProblem_JoinFetch() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch",Course.class).
                getResultList();
        for(Course course:courses){
            logger.info("Course -> {} Student -> {}",course,course.getStudents());
        }

    }
}
