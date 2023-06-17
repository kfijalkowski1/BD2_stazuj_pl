package com.stazuj_pl.Employee;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CensoredEmployees")
public class CensoredEmployee extends EntityObj {
    // pola z DB
    @Id
    private int employee_id;

    private String name;
    private String surname;
    private String login;
    private String about_me;
    private String plan_type;
    private String company_name;

    public int getUser_employee_id() {
        return user_employee_id;
    }

    public void setUser_employee_id(int user_employee_id) {
        this.user_employee_id = user_employee_id;
    }

    private int user_employee_id;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

}