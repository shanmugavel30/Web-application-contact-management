package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dto.Contacts;

@WebServlet("/showContact")
public class ShowContacts extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		HttpSession ses = req.getSession();
		List<Contacts> searchContact = (List<Contacts>) ses.getAttribute("searchContacts");
		System.out.println(searchContact.toString());
		response.setContentType("application/json");
		JSONArray outerArray = new JSONArray();
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		for (int i = 0; i < searchContact.size(); ++i) {

			JSONObject obj = new JSONObject();
			obj.put("name", searchContact.get(i).getName());
			obj.put("phoneno", searchContact.get(i).getPhoneNo());
			obj.put("address", searchContact.get(i).getAddress());
			obj.put("favState", searchContact.get(i).getFavState());
			outerArray.add(obj);
		}
		json.put("details", outerArray);
		out.print(json);
	}
}
