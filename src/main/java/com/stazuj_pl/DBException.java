package com.stazuj_pl;

public class DBException extends Exception {
    public DBException(String errorMessage) {
        super(errorMessage);
    }
}
