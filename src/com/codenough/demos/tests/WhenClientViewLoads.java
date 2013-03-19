package com.codenough.demos.tests;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.codenough.demos.swingmvp.data.ClientRepository;
import com.codenough.demos.swingmvp.models.ClientModel;
import com.codenough.demos.swingmvp.presenters.ClientsPresenter;
import com.codenough.demos.swingmvp.views.ClientsView;
import com.codenough.demos.swingmvp.views.ClientsViewListener;

public class WhenClientViewLoads {
	private ClientsView clientsViewMock;
	private ClientRepository clientRepositoryMock;
	private ClientsPresenter clientsPresenter;
	
	private final List<ClientModel> testClients;
	
	public WhenClientViewLoads() {
		this.testClients = new ArrayList<ClientModel>();
		
		this.testClients.add(new ClientModel(1, "Oliver Stone", 28, "Male", "oston@test.com"));
		this.testClients.add(new ClientModel(2, "Mallory Owen", 22, "Female", "mow@test.com"));
	}
	
	@Before
	public void setUp() {
		this.clientsViewMock = mock(ClientsView.class);
		this.clientRepositoryMock = mock(ClientRepository.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void itShouldLoadAllClients() {
		// Act
		this.clientsPresenter = new ClientsPresenter(this.clientsViewMock, this.clientRepositoryMock);
		
		// Assert
		verify(this.clientsViewMock).loadClients(any(List.class));
	}
	
	@Test
	public void itShouldShowFirstClientOnListDetails() {
		// Arrange
		when(this.clientRepositoryMock.findAll()).thenReturn(this.testClients);
		
		// Act
		this.clientsPresenter = new ClientsPresenter(this.clientsViewMock, this.clientRepositoryMock);
		
		// Assert
		verify(this.clientsViewMock).loadClient(any(ClientModel.class));
	}
	
	@Test
	public void itShouldShowClientDetailsOnListItemSelected() {
		// Arrange
		when(this.clientRepositoryMock.findAll()).thenReturn(this.testClients);
		when(this.clientRepositoryMock.getById(1)).thenReturn(this.testClients.get(0));
		when(this.clientsViewMock.getSelectedClient()).thenReturn(this.testClients.get(0));
		
		this.clientsPresenter = new ClientsPresenter(this.clientsViewMock, this.clientRepositoryMock);
		
		// Act
		this.clientsPresenter.ClientSelected();
		
		// Assert
		verify(this.clientsViewMock, atLeastOnce()).loadClient(this.testClients.get(0));
	}
}
