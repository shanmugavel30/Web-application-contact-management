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
import com.login.LoginController;
import com.repository.ContactWebRepository;

/**
 * Servlet implementation class contact
 */
@WebServlet("/contact")
public class contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public contact() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new LoginController().checkUser(request.getParameter("username"), request.getParameter("password"), request,
				response);
	}

	public void loginSuccess(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Contacts> searchContact = ContactWebRepository.getInstance().getContact();
//		System.out.println(Searchcontact);
		HttpSession ses = request.getSession();
		ses.setAttribute("searchContacts", searchContact);
//		response.setStatus(200);
		PrintWriter out = response.getWriter();
		out.append(s);

	}

	public void loginFailure(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.append(s);
	}

}
///**
//* Servlet implementation class ContactServlet
//*/
//@webServlet("/contact")
//public class ContactServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    
// /**
//  * @see HttpServlet#HttpServlet()
//  */
// public ContactServlet() {
//     super();
//     // TODO Auto-generated constructor stub
// }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 **/
// protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
// 	System.out.println("poda dai!!");
//		new LoginController().checkUser(request.getParameter("username"), request.getParameter("password"),request,response);
//	}
//
//	public void loginSuccess(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println("reach");
//		List<Contacts> Searchcontact=ContactWebRepository.getInstance().getContact();
//		System.out.println(Searchcontact);
//		response.setStatus(200);
//		response.setContentType("text/plain");
//		PrintWriter out = response.getWriter();
//		out.append(s);
//		
//	}
//	
//
//	public void loginFailure(String s, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setStatus(200);
//		response.setContentType("text/plain");
//		PrintWriter out = response.getWriter();
//		out.append(s);
//	}
//
//}
