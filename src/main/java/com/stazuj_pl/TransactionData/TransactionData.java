package com.stazuj_pl.TransactionData;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TransactionData")
public class TransactionData extends EntityObj {

    // pola z DB
    @Id
    private int transaction_data_id;
    private int internship_ad_id;
    private int cv_id;
    private String state;

    public int getTransaction_data_id() {
        return transaction_data_id;
    }

    public void setTransaction_data_id(int transaction_data_id) {
        this.transaction_data_id = transaction_data_id;
    }

    public int getInternship_ad_id() {
        return internship_ad_id;
    }

    public void setInternship_ad_id(int internship_ad_id) {
        this.internship_ad_id = internship_ad_id;
    }

    public int getCv_id() {
        return cv_id;
    }

    public void setCv_id(int cv_id) {
        this.cv_id = cv_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

