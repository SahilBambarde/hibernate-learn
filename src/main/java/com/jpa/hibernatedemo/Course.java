package com.jpa.hibernatedemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name="CourseDetails")
@NamedQuery(name="query_get_all_courses",query="Select c From Course c")
@NamedQuery(name="query_get_all_courses_join_fetch",query="Select c From Course c JOIN FETCH c.students s")
@NamedQuery(name="query_get_100_Step_courses",query="Select c From Course c where name like '%100 Steps'")
//@Cacheable
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause="is_deleted =false")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="fullname", nullable=false)
    private String name;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private boolean isDeleted;
    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
    @PreRemove
    private void preRemove(){
        LOGGER.info("Setting isDelete  to True");
        this.isDeleted=true;
    }

    @OneToMany(mappedBy="course", fetch=FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy="courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public void removeReview(Review review){
        this.reviews.remove(review);
    }

    protected Course(){}

    public Course(String name){
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
