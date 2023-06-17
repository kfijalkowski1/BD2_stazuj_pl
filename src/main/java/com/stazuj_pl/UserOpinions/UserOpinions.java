package com.stazuj_pl.UserOpinions;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TransactionData")
public class UserOpinions extends EntityObj {

    // pola z DB
    @Id
    private int opinion_id;
    private int user_id;
    private int company_id;
    private String content;
    private int rating;

    public int getOpinion_id() {
        return opinion_id;
    }

    public void setOpinion_id(int opinion_id) {
        this.opinion_id = opinion_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

