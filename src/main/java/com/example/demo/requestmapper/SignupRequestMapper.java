package com.example.demo.requestmapper;

import com.example.demo.model.SignupRequest;
import jakarta.servlet.http.HttpServletRequest;

public class SignupRequestMapper {
    public static SignupRequest map(HttpServletRequest request) {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFname(request.getParameter("fname"));
        signupRequest.setMail(request.getParameter("mail"));
        signupRequest.setNum(request.getParameter("num"));
        signupRequest.setUname(request.getParameter("uname"));
        signupRequest.setPwd(request.getParameter("pwd"));
        signupRequest.setCpwd(request.getParameter("cpwd"));
        signupRequest.setHashedPwdFromJSP(request.getParameter("hashedPwd"));
        signupRequest.setHashedCPwd(request.getParameter("hashedCPwd"));
        return signupRequest;
    }
}
