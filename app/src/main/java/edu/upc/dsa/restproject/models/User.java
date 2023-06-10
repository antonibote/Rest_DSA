package edu.upc.dsa.restproject.models;

public class User {
    String idUser;
    String name;
    String surname;
    String email;
    String password;

    public User() {    }

    public User(String idUser,String name, String surname, String email, String password) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    public String getIdUser(){return idUser;}
    public void setIdUser(String idUser){this.idUser = idUser;}
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){return email;}
}