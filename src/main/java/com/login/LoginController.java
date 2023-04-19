package com.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Servlet.*;

public class LoginController {
	private LoginModel loginmodel;
	
	public LoginController() {
		loginmodel=new LoginModel(this);
	}
	
	public void checkUser(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		loginmodel.checkUser(username, password, request,response);
		
	}
	

	public void loginSuccess(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
		contact login = new contact();
		login.loginSuccess(s,request,response);
	}

	public void loginFailure(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
		contact login = new contact();
		login.loginFailure(s, request, response);
	}

	
}
