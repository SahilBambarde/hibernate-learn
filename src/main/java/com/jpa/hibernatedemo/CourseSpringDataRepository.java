package com.jpa.hibernatedemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course,Long> {
    List<Course> findByName(String name);
    List<Course> countByName(String name);
    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByNameOrderById(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

//    @Query("Select c From Course c where name like '%100 Steps")
//    List<Course> coursesWith100StepsInName();

//    @Query(value = "Select * From Course where name like '%100 Steps", nativeQuery=true)
//    List<Course> coursesWith100StepsInName2();

    @Query(value = "query_get_100_Step_courses", nativeQuery=true)
    List<Course> coursesWith100StepsInName_NamedQuery();

}
