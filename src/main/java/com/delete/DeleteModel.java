package com.delete;

import com.dto.Contacts;
import com.repository.ContactWebRepository;

public class DeleteModel {
	private DeleteController  delcontroller;
	public  DeleteModel(DeleteController  delcontroller) {
		this.delcontroller=delcontroller;
	}
	public boolean deleteContact(Contacts contact) {
		
		return ContactWebRepository.getInstance().deleteContact(contact);
	}
	
}
