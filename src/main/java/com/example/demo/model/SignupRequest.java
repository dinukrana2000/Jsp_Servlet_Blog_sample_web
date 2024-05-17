package com.example.demo.model;

public class SignupRequest {
    private String fname;
    private String mail;
    private String num;
    private String uname;
    private String pwd;
    private String cpwd;
    private String hashedPwdFromJSP;
    private String hashedCPwd;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCpwd() {
        return cpwd;
    }

    public void setCpwd(String cpwd) {
        this.cpwd = cpwd;
    }

    public String getHashedPwdFromJSP() {
        return hashedPwdFromJSP;
    }

    public void setHashedPwdFromJSP(String hashedPwdFromJSP) {
        this.hashedPwdFromJSP = hashedPwdFromJSP;
    }

    public String getHashedCPwd() {
        return hashedCPwd;
    }

    public void setHashedCPwd(String hashedCPwd) {
        this.hashedCPwd = hashedCPwd;
    }
}