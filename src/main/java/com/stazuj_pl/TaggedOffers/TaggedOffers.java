package com.stazuj_pl.TaggedOffers;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TaggedOffers")
public class TaggedOffers extends EntityObj {

    // pola z DB
    @Id
    private int tagged_offer_id;
    private int internship_ad_id;
    private int user_id; //student_id;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTagged_offer_id() {
        return tagged_offer_id;
    }

    public void setTagged_offer_id(int tagged_offer_id) {
        this.tagged_offer_id = tagged_offer_id;
    }

    public int getInternship_ad_id() {
        return internship_ad_id;
    }

    public void setInternship_ad_id(int internship_ad_id) {
        this.internship_ad_id = internship_ad_id;
    }
}

