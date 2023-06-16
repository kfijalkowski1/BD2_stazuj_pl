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
    private String employee_id;
    private String intenship_ad_id;
    private String student_id;

    // auto generated seters geters

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

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getIntenship_ad_id() {
        return intenship_ad_id;
    }

    public void setIntenship_ad_id(String intenship_ad_id) {
        this.intenship_ad_id = intenship_ad_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

}

