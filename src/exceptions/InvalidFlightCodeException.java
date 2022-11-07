package exceptions;

/**
 * Exception caused by an invalid flight code format
 * @author TravisM
 *
 */
public class InvalidFlightCodeException extends Exception {
    private String invalidFlightCode;
	public InvalidFlightCodeException(String invalidFlightCode){
		super("Flight Code " + invalidFlightCode + " is invalid" );
		this.invalidFlightCode=invalidFlightCode;
	}
}

