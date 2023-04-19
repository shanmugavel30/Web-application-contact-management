package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Contacts;
import com.dto.ErrorMessage;
import com.edit.EditController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ObjectMapper objectMapper = new ObjectMapper();
		Contacts contact = objectMapper.readValue(request.getReader(), Contacts.class);
		ErrorMessage msg = new ErrorMessage();

		boolean editRes = new EditController().editContact(contact);

		if (editRes) {
			msg.setMsg("Updated Successfully");

		} else {
			msg.setMsg("Updation failed...!Please try again...");
		}
		response.setContentType("application/json");
		 String jsonString = objectMapper.writeValueAsString(msg);
		 System.out.println("msg");
		response.getWriter().append(jsonString).flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		//response.getWriter().append(String.valueOf(editRes)).flush();
	}

}
