package com.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dto.Contacts;

public class ContactWebRepository {
	private Connection connection;
	private static ContactWebRepository repository;

	private ContactWebRepository() {
		connect();
	}

	private void connect() {
		String url = "jdbc:mysql://localhost:3306/Contact";
		String username = "root";
		String password = "Selvam@20";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	public static ContactWebRepository getInstance() {
		if (repository == null) {
			repository = new ContactWebRepository();
		}
		return repository;
	}

	public String checkUser(String username, String password) {
		String s = "Invalid Username";
//			System.out.println(username);

		if (username.equals("shanmugavel")) {
			s = password.equals("selvam@20") ? "true" : "Invalid Password";
		}
		return s;
	}

	public List<Contacts> getContact() {

		String query = "select * from peoples";
		List<Contacts> searchcontacts = new ArrayList<>();
		try {

			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				long phoneNo = Long.parseLong(rs.getString("PhoneNum"));
				Contacts temp = new Contacts(rs.getString("username"), phoneNo, rs.getString("address"),
						rs.getString("favState"));
				searchcontacts.add(temp);
			}
			return searchcontacts;
		} catch (SQLException e) {
			e.getMessage();
		}
		return null;
	}
	
	public boolean editContact(Contacts contact) throws SQLException {
		String check="select * from peoples where username=?";
		String update="update peoples set address=?,phoneNum=?,favState=? where username=?";
		String insert="insert into peoples(address,phoneNum,favState,username) values(?,?,?,?)";
		PreparedStatement stmt;
		
		stmt=connection.prepareStatement(check);
		stmt.setString(1,contact.getName());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			stmt=connection.prepareStatement(update);
		}else {
			stmt=connection.prepareStatement(insert);
		}
		stmt.setString(1, contact.getAddress());
		stmt.setString(2, String.valueOf(contact.getPhoneNo()));
		stmt.setString(3, contact.getFavState());
		stmt.setString(4, contact.getName().strip());
		
		int affectedRows = stmt.executeUpdate();
		if(affectedRows>0) {
			System.out.println("true");
			return true;
		}
		
		return false;
	}
	
	public boolean deleteContact(Contacts contact) {
		String query="delete from peoples where username=?";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, contact.getName());
			int affectedRows = stmt.executeUpdate();
			if(affectedRows>0) {
				System.out.println("true");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public List<Contacts> searchContact(Contacts contact){
		String query="select * from peoples where username like ? OR phoneNum like ?" ;
		
		List<Contacts> searchcontacts = new ArrayList<>();
		try {

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, "%"+contact.getName()+"%");
			stmt.setString(2, "%"+contact.getName()+"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Contacts temp = new Contacts(rs.getString("username"),Long.parseLong(rs.getString("PhoneNum")), rs.getString("address"),
						rs.getString("favState"));
				searchcontacts.add(temp);
			}
			return searchcontacts;
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		
		return searchcontacts;
	}
	
//	public static void main(String[] args) throws SQLException {
//		Contacts contact=new Contacts("vijay",88293020L,"3/180,Thamira barani","yes");
//		ContactWebRepository.getInstance().editContact(contact);
//	}

}
