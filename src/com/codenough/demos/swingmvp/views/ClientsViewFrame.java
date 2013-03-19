package com.codenough.demos.swingmvp.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.codenough.demos.swingmvp.models.ClientModel;

public class ClientsViewFrame extends JFrame implements ClientsView {

	private static final long serialVersionUID = -3497803692721674406L;

	private List<ClientModel> clients;
	private final List<ClientsViewListener> listeners;
	
	private JList<ClientModel> clientsList;
	private JLabel clientNameLabel;
	private JLabel clientEmailLabel;
	private JLabel clientGenderLabel;
	private JLabel clientAgeLabel;
	private JPanel clientDetailsPanel;
	private JTextField clientNameTextBox;
	private JTextField clientEmailTextBox;
	private JTextField clientGenderTextBox;
	private JTextField clientAgeTextBox;
	private JButton closeButton;
	
	public ClientsViewFrame() {
		this.clients = null;
		this.listeners = new ArrayList<ClientsViewListener>();
		
		this.initializeComponent();
		this.bindComponent();
	}
	
	public List<ClientsViewListener> getListeners() {
		return this.listeners;
	}
	
	@Override
	public List<ClientModel> getClients() {
		return this.clients;
	}

	@Override
	public ClientModel getSelectedClient() {
		return this.clientsList.getSelectedValue();
	}

	@Override
	public void loadClient(ClientModel client) {
		this.clientNameTextBox.setText(client.getName());
		this.clientEmailTextBox.setText(client.getEmail());
		this.clientGenderTextBox.setText(client.getGender());
		this.clientAgeTextBox.setText(Integer.toString(client.getAge()));
	}

	@Override
	public void loadClients(List<ClientModel> clients) {
		DefaultListModel<ClientModel> clientListModel = new DefaultListModel<ClientModel>();
		
		for (ClientModel client : clients) {
			clientListModel.addElement(client);
		}
		
		this.clientsList.setModel(clientListModel);
	}

	@Override
	public void addClientSelectedListener(ClientsViewListener listener) {
		this.listeners.add(listener);
	}
	
	private void initializeComponent() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(0,0,0,0));
		
		this.clientDetailsPanel = new JPanel();
		this.clientsList = new JList<ClientModel>();
		this.closeButton = new JButton();
		
		GridBagLayout contentLayout = new GridBagLayout();
		
		contentLayout.rowHeights = new int[] {150, 0, 0};
		
		this.getContentPane().setLayout(contentLayout);
		
		GridBagConstraints contentConstraints = new GridBagConstraints();
		
		contentConstraints.gridx = 0;
		contentConstraints.gridy = 0;
		contentConstraints.weightx = 1.0;
		contentConstraints.weighty = 1.0;
		contentConstraints.insets = new Insets(10, 10, 10, 10);
		contentConstraints.fill = GridBagConstraints.BOTH;
		
		this.getContentPane().add(this.clientsList, contentConstraints);
		
		GroupLayout detailsLayout = new GroupLayout(this.clientDetailsPanel);
		
		this.clientDetailsPanel.setLayout(detailsLayout);
		
		this.clientNameLabel = new JLabel("Name");
		this.clientEmailLabel = new JLabel("E-mail");
		this.clientGenderLabel = new JLabel("Gender");
		this.clientAgeLabel = new JLabel("Age");
		this.clientNameTextBox = new JTextField(20);
		this.clientEmailTextBox = new JTextField(18);
		this.clientGenderTextBox = new JTextField(12);
		this.clientAgeTextBox = new JTextField(8);
		
		detailsLayout.setAutoCreateGaps(true);
		
		detailsLayout.setHorizontalGroup(
			detailsLayout.createSequentialGroup()
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(this.clientNameLabel)
					.addComponent(this.clientEmailLabel)
					.addComponent(this.clientGenderLabel)
					.addComponent(this.clientAgeLabel))
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(this.clientNameTextBox)
					.addComponent(this.clientEmailTextBox)
					.addComponent(this.clientGenderTextBox)
					.addComponent(this.clientAgeTextBox))
		);
		
		detailsLayout.setVerticalGroup(
			detailsLayout.createSequentialGroup()
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(this.clientNameLabel)
					.addComponent(this.clientNameTextBox))
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(this.clientEmailLabel)
					.addComponent(this.clientEmailTextBox))
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(this.clientGenderLabel)
					.addComponent(this.clientGenderTextBox))
				.addGroup(detailsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(this.clientAgeLabel)
					.addComponent(this.clientAgeTextBox))
		);
		
		contentConstraints.gridy = 1;
		
		this.getContentPane().add(this.clientDetailsPanel, contentConstraints);
		
		contentConstraints.gridy = 2;
		contentConstraints.fill = GridBagConstraints.NONE;
		contentConstraints.anchor = GridBagConstraints.EAST;
		
		this.closeButton.setText("Close");
		
		this.getContentPane().add(this.closeButton, contentConstraints);
		
		this.setTitle("Clients");
		this.setResizable(false);
		this.pack();
	}
	
	private void bindComponent() {
		this.clientsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				for (ClientsViewListener listener : getListeners()) {
					listener.ClientSelected();
				}
			}
		});
		
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (ClientsViewListener listener : getListeners()) {
					listener.Closed();
				}
			}			
		});
	}
}
