package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import manager.ReservationManager;
import problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	JTextField codeField;
	JTextField airlineField;
	JTextField nameField;
	
	JTextField eastCodeField;
	JTextField eastFlightField;
	JTextField eastAirlineField;
	JTextField costField;
	JTextField eastNameField;
	JTextField citizenshipField;
	JComboBox statusBox;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	
	/**
	 * Creates the components for reservations tab.
	 */
	private DefaultListModel<Reservation> reservationsModel;
	
	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates components for Southern Panel of Reservation Tab
	 * @return Southern Reservation Panel
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Reserve", SwingConstants.CENTER);
		label.setFont(new Font("serif", Font.PLAIN, 24));
		panel.add(label, BorderLayout.NORTH);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//The Code label
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = .01;
		gridPanel.add(codeLabel, constraints);
		//The Code text field
		codeField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 5;
		gridPanel.add(codeField, constraints);

		//The Airline Label
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = .01;
		gridPanel.add(airlineLabel, constraints);
		//The Airline text field
		airlineField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 5;
		gridPanel.add(airlineField, constraints);

		//The Name Label
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = .01;
		gridPanel.add(nameLabel, constraints);
		//The Name text field
		nameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.weightx = 5;
		gridPanel.add(nameField, constraints);

		panel.add(gridPanel, BorderLayout.CENTER);
		
		JButton reserveButton = new JButton("Find Reservation");
		reserveButton.addActionListener(new reservationFinderListener());
		panel.add(reserveButton, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Listener Class for Reservation Finder
	 */
	private class reservationFinderListener implements ActionListener  {
		String code;
		String airline;
		String name;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Read inputs
			code = (String) codeField.getText();
			airline = (String) airlineField.getText();
			name = (String) nameField.getText();
			
			//Invokes Find Reservation Method
			reservationsModel.addAll(reservationManager.findReservation(code, airline, name));
		}
	}

	/**
	 * Creates components for Eastern Panel
	 * @return Eastern Reservation Panel
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Reserve", SwingConstants.CENTER);
		label.setFont(new Font("serif", Font.PLAIN, 24));
		panel.add(label, BorderLayout.NORTH);
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//The Code label
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridPanel.add(codeLabel, constraints);
		//The Code text field
		eastCodeField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		eastCodeField.setEditable(false);
		gridPanel.add(eastCodeField, constraints);
		
		//The Flight label
		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		gridPanel.add(flightLabel, constraints);
		//The Flight text field
		eastFlightField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		eastFlightField.setEditable(false);
		gridPanel.add(eastFlightField, constraints);
		
		//The Airline label
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		gridPanel.add(airlineLabel, constraints);
		//The Airline text field
		eastAirlineField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		eastAirlineField.setEditable(false);
		gridPanel.add(eastAirlineField, constraints);
		
		//The Cost Label
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		gridPanel.add(costLabel, constraints);
		//The Cost text field
		costField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		costField.setEditable(false);
		gridPanel.add(costField, constraints);
		
		//The Name Label
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		gridPanel.add(nameLabel, constraints);
		//The Name text field
		eastNameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		gridPanel.add(eastNameField, constraints);
		
		//Citizenship Label
		JLabel citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		gridPanel.add(citizenshipLabel, constraints);
		//Citizenship Text Field
		citizenshipField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 5;
		gridPanel.add(citizenshipField, constraints);
		
		//Status Combo Box Label
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		gridPanel.add(statusLabel, constraints);
		//Status Combo Box
		String[] status = {"Active", "Inactive"};
		statusBox = new JComboBox(status);
		constraints.gridx = 1;
		constraints.gridy = 6;
		gridPanel.add(statusBox, constraints);
		
		panel.add(gridPanel, BorderLayout.CENTER);
		
		JButton reserveButton = new JButton("Update");
		reserveButton.addActionListener(new reservationUpdateListener());
		panel.add(reserveButton, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private class reservationUpdateListener implements ActionListener  {
		String name;
		String citizenship;
		String isActive;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Validates List Selection
			if(reservationsList.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(panel, "Please Select a Reservation");
				return;
			}
			
			//Reads Text Fields
			name = (String) eastNameField.getText();
			citizenship = (String) citizenshipField.getText();
			isActive =  (String) statusBox.getSelectedItem();
			
			//Validates Name Field
			if(name.equals("")) {
				JOptionPane.showMessageDialog(panel, "Please enter passenger name");
				return;
			}
			//Validates Citizenship Field
			if(citizenship.equals("")) {
				JOptionPane.showMessageDialog(panel, "Please enter passenger citizenship");
				return;
			}
			
			//Reads Status Combo box and sets isActive
			if (isActive == "Inactive") {
				reservationsList.getSelectedValue().setActive(false);
			}
			
			//Sets Name and Citizenship
			reservationsList.getSelectedValue().setName(name);
			reservationsList.getSelectedValue().setCitizenship(citizenship);
			
			//Save to Binary
			reservationManager.persist();
			
			//Confirm Update for User
			JOptionPane.showMessageDialog(panel, "Your Reservation has been updated!");
		}
		 
	}
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,15,40,15));
		 
		reservationsModel = new DefaultListModel<>();

		reservationsList = new JList<>(reservationsModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
				eastCodeField.setText(reservationsList.getSelectedValue().getCode());
				eastFlightField.setText(reservationsList.getSelectedValue().getFlightCode());
				eastAirlineField.setText(reservationsList.getSelectedValue().getAirline());
				Double cost = reservationsList.getSelectedValue().getCost();
				costField.setText("$" + cost.toString());
				eastNameField.setText(reservationsList.getSelectedValue().getName());
				citizenshipField.setText(reservationsList.getSelectedValue().getCitizenship());
				if(reservationsList.getSelectedValue().isActive()) {	
					statusBox.setSelectedItem("Active");
				} else
					statusBox.setSelectedItem("Inactive");
				}
			}
		
		}
		
	}
