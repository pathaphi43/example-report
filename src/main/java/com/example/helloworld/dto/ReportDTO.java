package com.example.helloworld.dto;

import com.example.helloworld.entity.Student;
import com.example.helloworld.entity.Teacher;

import java.util.List;

public class ReportDTO {
    private Integer studentId;
    private Integer teacher_id;
    private String studentName;
    private String studentLastname;
    private String studentPhone;
    private String studentProgram;

    private Integer teacherId;
    private Integer subject_id;
    private String teacherName;
    private String teacherLastname;
    private String teacherPhone;

    private Integer subjectId;
    private String subjectName;
    private String subjectDepartment;

    private List<Student> studentList;



    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }



    public  ReportDTO(){

    }

    public ReportDTO(Integer studentId, Integer teacher_id, String studentName, String studentLastname, String studentPhone, String studentProgram, Integer teacherId, Integer subject_id, String teacherName, String teacherLastname, String teacherPhone, Integer subjectId, String subjectName, String subjectDepartment) {
        this.studentId = studentId;
        this.teacher_id = teacher_id;
        this.studentName = studentName;
        this.studentLastname = studentLastname;
        this.studentPhone = studentPhone;
        this.studentProgram = studentProgram;
        this.teacherId = teacherId;
        this.subject_id = subject_id;
        this.teacherName = teacherName;
        this.teacherLastname = teacherLastname;
        this.teacherPhone = teacherPhone;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDepartment = subjectDepartment;
    }
    //forPDF
    public ReportDTO(Integer id, String teacherName, String teacherLastname, String subjectName, List<Student> students) {
        this.studentList = students;
        this.teacherId = id;
        this.teacherName = teacherName;
        this.teacherLastname = teacherLastname;
        this.subjectName = subjectName;
    }

    public ReportDTO(Integer studentId, Integer teacher_id, String studentName, String studentLastname, String studentPhone, String studentProgram, String teacherName, String teacherLastname) {
        this.studentId = studentId;
        this.teacher_id = teacher_id;
        this.studentName = studentName;
        this.studentLastname = studentLastname;
        this.studentPhone = studentPhone;
        this.studentProgram = studentProgram;
        this.teacherName = teacherName;
        this.teacherLastname = teacherLastname;
    }




    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public void setStudentLastname(String studentLastname) {
        this.studentLastname = studentLastname;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(String studentProgram) {
        this.studentProgram = studentProgram;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLastname() {
        return teacherLastname;
    }

    public void setTeacherLastname(String teacherLastname) {
        this.teacherLastname = teacherLastname;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDepartment() {
        return subjectDepartment;
    }

    public void setSubjectDepartment(String subjectDepartment) {
        this.subjectDepartment = subjectDepartment;
    }
}
