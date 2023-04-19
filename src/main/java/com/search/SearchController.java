package com.search;

import java.util.List;

import com.dto.Contacts;

public class SearchController {

	private SearchModel searchmodel;
	
	public SearchController() {
		searchmodel=new SearchModel(this);
	}

	public List<Contacts> searchContact(Contacts contact) {

		return searchmodel.searchContact(contact);
	}
	
}
