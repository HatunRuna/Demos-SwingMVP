package com.codenough.demos.swingmvp.data;

import java.util.ArrayList;
import java.util.List;

import com.codenough.demos.swingmvp.models.ClientModel;

public class ClientRepository {
	private final List<ClientModel> clients;
	
	public ClientRepository() {
		this.clients = new ArrayList<ClientModel>();
		
		this.clients.add(new ClientModel(1, "Matt Dylan", 28, "Male", "mattd@none.com"));
		this.clients.add(new ClientModel(2, "Anna Stone", 22, "Female", "ann@none.com"));
	}
	
	public List<ClientModel> findAll() {
		return this.clients;
	}
	
	public ClientModel getById(int id) {
		for (ClientModel client : this.clients) {
			if (client.getId() == id) {
				return client;
			}
		}
		
		return null;
	}
}
