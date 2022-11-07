package exceptions;

/**
 * Exception caused when there are no more available seats on a selected flight
 * @author TravisM
 *
 */
public class NoMoreSeatsException extends Exception {

	public NoMoreSeatsException(String flightCode){
		super("There are no available seats left on the flight " + flightCode + " left!" );
	}
}
