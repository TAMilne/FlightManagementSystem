package gui;

import java.awt.*;
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

import exceptions.InvalidCitizenshipException;
import exceptions.NoMoreSeatsException;
import manager.FlightManager;
import manager.ReservationManager;
import problemdomain.Flight;
import problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	JComboBox fromBox;
	JComboBox toBox;
	JComboBox dayBox;
	JButton findButton;
	JTextField flightField;
	JTextField airlineField;
	JTextField dayField;
	JTextField timeField;
	JTextField costField;
	JTextField nameField;
	JTextField citizenshipField;
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
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
	 * Creates components for Flight Tab South Panel
	 * 
	 * @return South Panel
	 */
	private JPanel createSouthPanel() {
		// Load days and Airports to Combo Boxes
		Object[] airports = flightManager.getAirports().toArray();
		String[] days = { FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_SUNDAY, FlightManager.WEEKDAY_MONDAY,
				FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY, FlightManager.WEEKDAY_THURSDAY,
				FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY };

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel label = new JLabel("Flight Finder", SwingConstants.CENTER);
		label.setFont(new Font("serif", Font.PLAIN, 24));
		panel.add(label, BorderLayout.NORTH);

		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;

		/**
		 * Flight Finder
		 */
		// From Airport Label
		JLabel fromLabel = new JLabel("From:");
		fromLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = .01;
		gridPanel.add(fromLabel, constraints);
		// From Airport Combo Box
		fromBox = new JComboBox(airports);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 5;
		gridPanel.add(fromBox, constraints);

		// To Airport Label
		JLabel toLabel = new JLabel("To:");
		toLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = .01;
		gridPanel.add(toLabel, constraints);
		// To Airport Combo Box
		toBox = new JComboBox(airports);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 5;
		gridPanel.add(toBox, constraints);

		// Day of Week Label
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = .01;
		gridPanel.add(dayLabel, constraints);
		// Day of Week Combo Box
		dayBox = new JComboBox(days);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 5;
		gridPanel.add(dayBox, constraints);

		panel.add(gridPanel, BorderLayout.CENTER);

		findButton = new JButton("Find Flights");
		findButton.addActionListener(new FlightFinderListener());

		panel.add(findButton, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Listener class for Flight Finder Button
	 */
	private class FlightFinderListener implements ActionListener {
		String from;
		String to;
		String weekday;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Resets List from previous searches
			flightsModel.clear();

			// Reads input Fields
			from = (String) fromBox.getSelectedItem();
			to = (String) toBox.getSelectedItem();
			weekday = (String) dayBox.getSelectedItem();

			// Invokes Flight Finder Method
			flightsModel.addAll(flightManager.findFlights(from, to, weekday));
		}
	}

	/**
	 * Returns the East Panel of Flight Tab
	 * 
	 * @return East Panel
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

		// The Flight label
		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 0;
		gridPanel.add(flightLabel, constraints);
		// The Flight text field
		flightField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		flightField.setEditable(false);
		gridPanel.add(flightField, constraints);

		// The Airline label
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		gridPanel.add(airlineLabel, constraints);
		// The Airline text field
		airlineField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		airlineField.setEditable(false);
		gridPanel.add(airlineField, constraints);

		// The Day label
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		gridPanel.add(dayLabel, constraints);
		// the day text field
		dayField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		dayField.setEditable(false);
		gridPanel.add(dayField, constraints);

		// The Time Label
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		gridPanel.add(timeLabel, constraints);
		// The Time Text Field
		timeField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		timeField.setEditable(false);
		gridPanel.add(timeField, constraints);

		// The Cost Label
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		gridPanel.add(costLabel, constraints);
		// The Cost Text Field
		costField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		costField.setEditable(false);
		gridPanel.add(costField, constraints);

		// The Name Label
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		gridPanel.add(nameLabel, constraints);
		// The Name Text Field
		nameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 5;
		gridPanel.add(nameField, constraints);

		JLabel citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		gridPanel.add(citizenshipLabel, constraints);
		// the name text field
		citizenshipField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 6;
		gridPanel.add(citizenshipField, constraints);

		panel.add(gridPanel, BorderLayout.CENTER);

		JButton reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ReserveListener());
		panel.add(reserveButton, BorderLayout.SOUTH);

		panel.setSize(new Dimension(300, 300));

		return panel;
	}

	/**
	 * Listener Class for Reserve Button
	 */
	private class ReserveListener implements ActionListener {
		Flight f;
		String name;
		String citizenship;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Validates Flight List Selection
			if (flightsList.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(panel, "Please select an Available Flight");
				return;
			}

			// Reads inputs from fields
			f = flightsList.getSelectedValue();
			name = nameField.getText();
			citizenship = citizenshipField.getText();
			//Validate Name Field
			
			Reservation r;
			// Creates Reservation
			try {
				r = reservationManager.makeReservation(f, name, citizenship);
			} catch (InvalidCitizenshipException e2) {
				JOptionPane.showMessageDialog(panel, "Please Enter your Citizenship");
				return;
			} catch (NoMoreSeatsException e1) {
				JOptionPane.showMessageDialog(panel,
						"I'm sorry, there are no remaining seats on flight " + f.getCode());
				return;
			}

			// Confirms Reservation for User
			JOptionPane.showMessageDialog(panel, "Reservation created. Your code is " + r.getCode());
			reservationManager.persist();

		}
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 40, 15));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);

		return panel;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				flightField.setText(flightsList.getSelectedValue().getCode());
				airlineField.setText(flightsList.getSelectedValue().getAirlineName());
				dayField.setText(flightsList.getSelectedValue().getWeekday());
				timeField.setText(flightsList.getSelectedValue().getTime());
				Double cost = flightsList.getSelectedValue().getCostPerSeat();
				costField.setText("$" + cost.toString());
			}
		}
	}
}
