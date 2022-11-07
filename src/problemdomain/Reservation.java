package problemdomain;

/**
 * Reservation Class used for creating and describing Reservation Object
 * @author TravisM
 * @version March 25h, 2022
 * 
 * @param code Reservation Code
 * @param flightCode Flight Code
 * @param airline Airline
 * @param name Name
 * @param citizenship Citizenship
 * @param cost Cost
 * @param active Active
 */
public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	
	/**
	 * Builds Reservation Object with default values
	 */
	public Reservation() {
		super();
	}
	
	/**
	 * Builds Reservation Object with assigned values
	 * @param code Reservation Code
	 * @param flightCode Flight Code
	 * @param airline Airline
	 * @param name Name
	 * @param citizenship Citizenship
	 * @param cost Cost
	 * @param active Active
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * Returns Reservation Code
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns Reservation Flight Code
	 * @return flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Returns Reservation Airline
	 * @return airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Returns Reservation Name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns Reservation Citizenship
	 * @return citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Returns Reservation Cost
	 * @return cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Returns if the Reservation is Active or not
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets Reservation name
	 * @param name Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets Reservation Citizenship
	 * @param citizenship Citizenship
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * Sets Reservation to Active or Inactive
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Custom Reservation to String
	 */
	@Override
	public String toString() {
		return  code;
	}	
}
