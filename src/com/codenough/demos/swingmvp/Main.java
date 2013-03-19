package com.codenough.demos.swingmvp;

import javax.swing.JFrame;

import com.codenough.demos.swingmvp.data.ClientRepository;
import com.codenough.demos.swingmvp.presenters.ClientsPresenter;
import com.codenough.demos.swingmvp.views.ClientsViewFrame;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ClientsViewFrame clientsFrame = new ClientsViewFrame();
		ClientRepository clientRepository = new ClientRepository();
		ClientsPresenter clientsPresenter = new ClientsPresenter(clientsFrame, clientRepository);
		
		clientsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientsFrame.setVisible(true);
	}

}
