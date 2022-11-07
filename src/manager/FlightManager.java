package manager;


import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidFlightCodeException;
import problemdomain.Flight;

/**
 * Flight Manager Class user for Creating and managing Flight Manager Object
 * @author TravisM
 * @version March 25, 2022
 * 
 */

public class FlightManager  {
	final public static String WEEKDAY_ANY = "Any";
	final public static String WEEKDAY_SUNDAY = "Sunday";
	final public static String WEEKDAY_MONDAY = "Monday";
	final public static String WEEKDAY_TUESDAY = "Tuesday";
	final public static String WEEKDAY_WEDNESDAY = "Wednesday";
	final public static String WEEKDAY_THURSDAY = "Thursday";
	final public static String WEEKDAY_FRIDAY = "Friday";
	final public static String WEEKDAY_SATURDAY = "Saturday";
	
	private ArrayList<Flight> flights = new ArrayList();
	private ArrayList<String> airports = new ArrayList();
	
	/**
	 * Builds Flight Manager Object
	 * @throws FileNotFoundException throws if file isn't found when loading airports and Flights
	 */
	public FlightManager() throws FileNotFoundException  {
		populateFlights();
		populateAirports();
	}

	/**
	 * Returns ArraList of Flight Objects
	 * @return flights
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Returns ArrayList of Airports
	 * @return airports
	 */
	public ArrayList<String> getAirports() {
		return airports;
	}

	/**
	 * Method for finding an airport by it's Code
	 * @param code Code
	 * @return myAirport
	 */
	public String findAirportByCode(String code) {
		String myAirport = "";
		for(int i=0; i < airports.size(); i++) {
			if(airports.get(i).equals(code)) {
				myAirport = airports.get(i);
			}
		}
		return myAirport;
	}
	
	/**
	 * Method for finding a Flight by its Code
	 * @param code Code
	 * @return myCode
	 */
	public String findFlightByCode(String code) {
		String myCode = "";
		for(Flight f: flights) {
			if(f.getCode().equals(code)) {
				myCode = f.getCode();
			}
		}
		return myCode;
	}
	
	/**
	 * Method for finding Flights and populating an ArrayList with possible matches
	 * @param from From
	 * @param to To
	 * @param weekday Day of Week
	 * @return myFlights
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> myFlights = new ArrayList<>();
		for(Flight f: flights) {
			if(f.getFrom().equals(from) && f.getTo().equals(to)) {
					if(weekday.equalsIgnoreCase("Any")) {
						myFlights.add(f);
					}else if(f.getWeekday().equals(weekday)) {
						myFlights.add(f);
					}
			}
		}
		return myFlights;
	}
	
	/**
	 * Method for populating Flight ArrayList with csv data
	 * @throws FileNotFoundException Throws if no flights.csv is found
	 */
	private void populateFlights() throws FileNotFoundException {
		String path = "res/flights.csv";
		Scanner inFile = new Scanner(new File(path));
		
		while (inFile.hasNext()) {
			String line = inFile.nextLine();
			String[] flightFields = line.split(",");
			try {
				flights.add(new Flight(flightFields[0], flightFields[1], flightFields[2], flightFields[3], flightFields[4], flightFields[5], Integer.parseInt(flightFields[6]), Double.parseDouble(flightFields[7])));
			} catch (InvalidFlightCodeException e) {
				System.out.println("That is not a valid Flight Code");
			}
		}
		inFile.close();
	}
	
	
	/**
	 * Method for populating Airport ArrayList with csv data
	 * @throws FileNotFoundException Throws if no airports.csv is found
	 */
	private void populateAirports() throws FileNotFoundException {
		String path = "res/airports.csv";
		Scanner inFile = new Scanner(new File(path));
		
		while (inFile.hasNext()) {
			String line = inFile.nextLine();
			String[] airportFields = line.split(",");
			
			airports.add(airportFields[0]);
		}
		inFile.close();
	}
}

