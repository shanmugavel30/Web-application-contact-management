package com.edit;

import java.sql.SQLException;

import com.dto.Contacts;
import com.repository.ContactWebRepository;

public class EditModel {
	private EditController editcontroller;
	public EditModel(EditController editcontroller) {
		this.editcontroller=editcontroller;
	}
	public boolean editContact(Contacts contact) throws SQLException {
		
		return ContactWebRepository.getInstance().editContact(contact);
	}
	
}
