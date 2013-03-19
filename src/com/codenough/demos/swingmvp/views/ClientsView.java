package com.codenough.demos.swingmvp.views;

import java.util.List;

import com.codenough.demos.swingmvp.models.ClientModel;

public interface ClientsView {
	List<ClientModel> getClients();
	ClientModel getSelectedClient();
	
	void loadClient(ClientModel client);
	void loadClients(List<ClientModel> clients);
	void addClientSelectedListener(ClientsViewListener listener);
}
