package com.edit;

import java.sql.SQLException;

import com.dto.Contacts;

public class EditController {
	private EditModel editmodel;
	
	public EditController() {
		editmodel=new EditModel(this);
	}

	public boolean editContact(Contacts contact) {

		try {
			return editmodel.editContact(contact);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
