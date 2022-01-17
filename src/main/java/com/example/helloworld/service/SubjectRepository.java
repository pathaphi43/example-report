package com.example.helloworld.service;

import com.example.helloworld.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
