package com.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import com.repository.ContactWebRepository;

public class LoginModel {
	private LoginController logincontroller;
	
	public LoginModel(LoginController logincontroller) {
		this.logincontroller=logincontroller;
	}

	public void checkUser(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String s=ContactWebRepository.getInstance().checkUser(username,password);
		if(s.equals("true")) {
			logincontroller.loginSuccess(s,request,response);
		}
		else {
			logincontroller.loginFailure(s,request,response);
		}
	}
}
