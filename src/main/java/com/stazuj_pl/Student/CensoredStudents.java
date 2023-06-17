package com.stazuj_pl.Student;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CensoredStudents")
public class CensoredStudents extends EntityObj {
    // pola z DB
    @Id
    private int student_id;
    private String name;
    private String surname;
    private String login;
    private String about_me;
    private String looking_for_job;
    private String college_name;
    private String course_name;

    public int getUser_student_id() {
        return user_student_id;
    }

    public void setUser_student_id(int user_student_id) {
        this.user_student_id = user_student_id;
    }

    private int user_student_id;


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getLooking_for_job() {
        return looking_for_job;
    }

    public void setLooking_for_job(String looking_for_job) {
        this.looking_for_job = looking_for_job;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
