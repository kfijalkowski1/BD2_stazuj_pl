package com.stazuj_pl.TaggedCandidates;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TaggedCandidates")
public class TaggedCandidates extends EntityObj {

    // pola z DB
    @Id
    private int tagged_candidate_id;

    private String rating;
    private String internship_ad_id;
    private String user_id_employee;
    private String user_id_student;


    public int getTagged_candidate_id() {
        return tagged_candidate_id;
    }

    public void setTagged_candidate_id(int tagged_candidate_id) {
        this.tagged_candidate_id = tagged_candidate_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getInternship_ad_id() {
        return internship_ad_id;
    }

    public void setInternship_ad_id(String internship_ad_id) {
        this.internship_ad_id = internship_ad_id;
    }

    public String getUser_id_employee() {
        return user_id_employee;
    }

    public void setUser_id_employee(String user_id_employee) {
        this.user_id_employee = user_id_employee;
    }

    public String getUser_id_student() {
        return user_id_student;
    }

    public void setUser_id_student(String user_id_student) {
        this.user_id_student = user_id_student;
    }
}

