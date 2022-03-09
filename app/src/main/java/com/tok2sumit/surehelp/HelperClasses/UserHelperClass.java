package com.tok2sumit.surehelp.HelperClasses;
// This class is for Firebase.
public class UserHelperClass {
    String fullname,phoneno,email,username,password,date,gender;
    public UserHelperClass(){};

    public UserHelperClass(String fullname, String phoneno, String email, String username, String password, String date, String gender) {
        this.fullname = fullname;
        this.phoneno = phoneno;
        this.email = email;
        this.username = username;
        this.password = password;
        this.date = date;
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
