package com.example.helloworld.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Integer id;

    @Column(name = "subject_name", nullable = false, length = 50)
    private String subjectName;

    @Column(name = "subject_department", nullable = false, length = 100)
    private String subjectDepartment;

    public String getSubjectDepartment() {
        return subjectDepartment;
    }

    public void setSubjectDepartment(String subjectDepartment) {
        this.subjectDepartment = subjectDepartment;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}