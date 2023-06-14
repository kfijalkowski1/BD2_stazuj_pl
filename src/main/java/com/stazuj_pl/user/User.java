package com.stazuj_pl.user;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements EntityObj {

    // pola z DB
    @Id
    private int user_id;

    private String mail;
    private String hash_password;
    private String name;
    private String surname;
    private String login;
    private String photo_path;
    private String about_me;

    // Constructors, getters, and setters automatic generate
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getHash_password() { return hash_password;}

    public void setHash_password(String hash_password) { this.hash_password = hash_password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname;}

    public void setSurname(String surname) { this.surname = surname; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPhoto_path() { return photo_path;}

    public void setPhoto_path(String photo_path) { this.photo_path = photo_path; }

    public String getAbout_me() { return about_me; }

    public void setAbout_me(String about_me) { this.about_me = about_me; }

}

