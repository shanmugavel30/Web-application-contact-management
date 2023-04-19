package com.search;

import java.util.List;

import com.dto.Contacts;
import com.repository.ContactWebRepository;

public class SearchModel {

	private SearchController searchcontroller;
	
	public SearchModel(SearchController searchcontroller) {
		this.searchcontroller=searchcontroller;
	}

	public List<Contacts> searchContact(Contacts contact) {
		return ContactWebRepository.getInstance().searchContact(contact);
	}
}
