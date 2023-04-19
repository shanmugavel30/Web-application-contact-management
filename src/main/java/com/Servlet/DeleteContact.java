package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delete.DeleteController;
import com.dto.Contacts;
import com.dto.ErrorMessage;
import com.edit.EditController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeleteContact
 */
@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		Contacts contact = objectMapper.readValue(request.getReader(), Contacts.class);
		ErrorMessage msg = new ErrorMessage();

		boolean delRes = new DeleteController().deleteContact(contact);

		if (delRes) {
			msg.setMsg("Deleted Successfully");

		} else {
			msg.setMsg("Deletion failed...!Please try again...");
		}
		response.setContentType("application/json");
		 String jsonString = objectMapper.writeValueAsString(msg);
		 response.getWriter().append(jsonString).flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
