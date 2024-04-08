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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes=HibernateApplication.class)
class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    void native_queries_basic() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE",Course.class);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE -> {}",resultList);
    }

    @Test
    void native_queries_basic_isDeleted() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where is_deleted=false",Course.class);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE -> {}",resultList);
    }

    @Test
    void native_queries_basic_where() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id=?",Course.class);
        query.setParameter(1,1001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE where id=? -> {}",resultList);
    }

    @Test
    void native_queries_basic_named_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id",Course.class);
        query.setParameter("id",1001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE where id=? -> {}",resultList);
    }

    @Test
    @Transactional
    void native_queries_to_update() {
        Query query = em.createNativeQuery("Update COURSE set last_updated_date = CURRENT_TIME",Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}",noOfRowsUpdated);
    }
}
