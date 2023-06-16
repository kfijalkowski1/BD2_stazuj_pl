package com.stazuj_pl.Files;

import com.stazuj_pl.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Files")
public class Files extends EntityObj {

    // pola z DB
    @Id
    private int file_id;
    private int student_id;
    private String is_main_cv;
    private String file_path;
    private String file_type;

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getIs_main_cv() {
        return is_main_cv;
    }

    public void setIs_main_cv(String is_main_cv) {
        this.is_main_cv = is_main_cv;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }
}

