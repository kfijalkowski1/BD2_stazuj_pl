package com.stazuj_pl.Student;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Students")
public class Student extends EntityObj {
    // pola z DB
    @Id
    private int student_id;

    private int user_student_id;
    private String academic_info_id;
    private String academic_year;
    private String looking_for_job;
    private String keywords;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getUser_student_id() {
        return user_student_id;
    }

    public void setUser_student_id(int user_student_id) {
        this.user_student_id = user_student_id;
    }

    public String getAcademic_info_id() {
        return academic_info_id;
    }

    public void setAcademic_info_id(String academic_info_id) {
        this.academic_info_id = academic_info_id;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getLooking_for_job() {
        return looking_for_job;
    }

    public void setLooking_for_job(String looking_for_job) {
        this.looking_for_job = looking_for_job;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}


