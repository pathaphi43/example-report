package com.example.helloworld.service;

import com.example.helloworld.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    List<Teacher> findAllById(Integer id);

//    @Modifying
    @Query("select t,j,s from Teacher t, Student s, Subject j where t.id = :id and t.id = s.teacher.id and t.subject.id = j.id")
    List<Teacher> getAllTeacherById(Integer id);



}
