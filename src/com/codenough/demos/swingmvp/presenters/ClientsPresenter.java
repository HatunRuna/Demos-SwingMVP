package com.codenough.demos.swingmvp.presenters;

import java.util.List;

import com.codenough.demos.swingmvp.data.ClientRepository;
import com.codenough.demos.swingmvp.models.ClientModel;
import com.codenough.demos.swingmvp.views.*;

public class ClientsPresenter implements ClientsViewListener {
	private final ClientsView view;
	private final ClientRepository clientRepository;
	
	public ClientsPresenter(ClientsView view, ClientRepository clientRepository) {
		this.view = view;
		this.clientRepository = clientRepository;
		
		List<ClientModel> clients = this.clientRepository.findAll();
		
		this.view.addClientSelectedListener(this);
		this.view.loadClients(clients);
		
		if (clients != null && clients.size() > 0) {
			ClientModel client = clients.get(0);
			
			if (client != null) {
				this.view.loadClient(client);
			}
		}
	}
	
	@Override
	public void Closed() {
		System.exit(0);
	}

	@Override
	public void ClientSelected() {
		if (this.view.getSelectedClient() != null) {
			int id = this.view.getSelectedClient().getId();
			ClientModel client = this.clientRepository.getById(id);
			
			this.view.loadClient(client);
		}
	}
	
}
