package com.example.helloworld.service;

import com.example.helloworld.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("select s from Student s where s.teacher.id = :id")
    List<Student> findByTeacherId(Integer id);


    @Query("select s,t from Student s,Teacher  t where s.teacher.id = t.id")
    List<Student> findStudentsByTeacherIdEqualsOrderById();
}
