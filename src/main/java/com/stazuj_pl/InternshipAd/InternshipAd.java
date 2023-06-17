package com.stazuj_pl.InternshipAd;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InternshipAd")
public class InternshipAd extends EntityObj {

    // pola z DB
    @Id
    private int internship_ad_id;

    private String internship_description;
    private String publication_date;
    private String position_type;
    private String salary_min;
    private String salary_max;
    private String employment_type;
    private String work_type;
    private String keywords;
    private int address_id;
    private int user_id; //employee_id
    private String expiration_date;
    private String duration;

    // Constructors, getters, and setters automatic generate

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getInternship_ad_id() {
        return internship_ad_id;
    }

    public void setInternship_ad_id(int internship_ad_id) {
        this.internship_ad_id = internship_ad_id;
    }

    public String getInternship_description() {
        return internship_description;
    }

    public void setInternship_description(String internship_description) {
        this.internship_description = internship_description;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getPosition_type() {
        return position_type;
    }

    public void setPosition_type(String position_type) {
        this.position_type = position_type;
    }

    public String getSalary_min() {
        return salary_min;
    }

    public void setSalary_min(String salary_min) {
        this.salary_min = salary_min;
    }

    public String getSalary_max() {
        return salary_max;
    }

    public void setSalary_max(String salary_max) {
        this.salary_max = salary_max;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

