package exceptions;

public class InvalidCitizenshipException extends Exception {

	/**
	 * Exception caused when Citizenship field is null or ""
	 * @author TravisM
	 */
	public InvalidCitizenshipException() {
		super("Please enter country you are a citizen of");
	}
}
