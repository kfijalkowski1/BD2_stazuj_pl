package com.stazuj_pl.academicInfo;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class AcademicInfo extends EntityObj {
    // pola z DB
    @Id
    private int academic_info_id;
    private int address_id;
    private String college_name;
    private String college_shortname;
    private String faculty_name;
    private String faculty_shortname;
    private String course_name;
    private String course_shortname;
    private String course_description;

    public int getAcademic_info_id() {
        return academic_info_id;
    }

    public void setAcademic_info_id(int academic_info_id) {
        this.academic_info_id = academic_info_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String collage_name) {
        this.college_name = collage_name;
    }

    public String getCollege_shortname() {
        return college_shortname;
    }

    public void setCollege_shortname(String collage_shortname) {
        this.college_shortname = collage_shortname;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getFaculty_shortname() {
        return faculty_shortname;
    }

    public void setFaculty_shortname(String faculty_shortname) {
        this.faculty_shortname = faculty_shortname;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_shortname() {
        return course_shortname;
    }

    public void setCourse_shortname(String course_shortname) {
        this.course_shortname = course_shortname;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }
}