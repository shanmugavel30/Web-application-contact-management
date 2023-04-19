package com.delete;

import com.dto.Contacts;

public class DeleteController {

	private DeleteModel deletemodel;
	public  DeleteController() {
		deletemodel=new DeleteModel(this);
	}
	public boolean deleteContact(Contacts contact) {
		return deletemodel.deleteContact(contact);
	}
	
	
}
