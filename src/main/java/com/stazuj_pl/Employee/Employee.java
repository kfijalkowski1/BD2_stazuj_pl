package com.stazuj_pl.Employee;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee extends EntityObj {
    // pola z DB
    @Id
    private int employee_id;
    private int company_id;
    private int user_employee_id;
    private String search_number;
    private String plan_type;
    private String message_template;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getUser_employee_id() {
        return user_employee_id;
    }

    public void setUser_employee_id(int user_employee_id) {
        this.user_employee_id = user_employee_id;
    }

    public String getSearch_number() {
        return search_number;
    }

    public void setSearch_number(String search_number) {
        this.search_number = search_number;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getMessage_template() {
        return message_template;
    }

    public void setMessage_template(String message_template) {
        this.message_template = message_template;
    }
}