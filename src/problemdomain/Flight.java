package problemdomain;

import exceptions.InvalidFlightCodeException;

/**
 * Class Flight for creating and describing Flight Object
 * @author TravisM
 * @version March 25, 2022
 *
 */
public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	
	/**
	 * Builds Flight Object with assigned values
	 * @param code Code
	 * @param airlineName Airline
	 * @param from From
	 * @param to To
	 * @param weekday Day of Week
	 * @param time Time
	 * @param seats Seats
	 * @param costPerSeat Cost per Seat
	 * @throws InvalidFlightCodeException 
	 */
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats, double costPerSeat) throws InvalidFlightCodeException {
		super();
		parseCode(code);
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Returns Flight code
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns Flight Airline
	 * @return airline
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * Returns departing Airport
	 * @return from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Returns arriving Airport
	 * @return airport
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Returns day of Flight
	 * @return weekDay
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Returns time of Flight
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Returns total Seats on Flight
	 * @return seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Returns cost per seat
	 * @return costPerSeat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
	
	/**
	 * Returns if flight is domestic or international
	 * @return isDomestic
	 */
	public boolean isDomestic() {
		if(to.charAt(0) =='Y' && from.charAt(0)=='Y') {
			return true;
		}else
			return false;
	}
	
	/**
	 * Test Format of Flight code
	 * @param flightCode Flight Code
	 * @throws InvalidFlightCodeException If code doesn't match format, exception is thrown
	 */
	public static void parseCode(String flightCode) throws InvalidFlightCodeException {
		if(!flightCode.matches("[A-Z]{2}[-][0-9]{4}") ) {
	    	throw new InvalidFlightCodeException(flightCode);
	    }
	}
	
	/**
	 * Custom Flight to String
	 */
	@Override
	public String toString() {
		return  code +  ", From:" + from + ", To:=" + to + ", Day:"
				+ weekday +  ", Cost" + costPerSeat;
	}
	
	
}
