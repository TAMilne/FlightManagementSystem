package manager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import exceptions.InvalidCitizenshipException;
import exceptions.NoMoreSeatsException;
import problemdomain.Flight;
import problemdomain.Reservation;

/**
 * Reservation Manager Class used for creating and managing Reservations Manager Object
 * @author TravisM
 * @version March 25, 2022
 * 
 */

public class ReservationManager {
	private ArrayList<Reservation> reservations = new ArrayList();
	private static final String RESERVATIONS_BINARY = "res/reservations.bin";
	private static final long RESO_SIZE = 156;

	
	/**
	 * Builds Reservation Manager Object
	 * 
	 * @throws IOException throws if the is an IO Exception
	 */
	public ReservationManager() throws IOException {
			populateFromBinary();
	}

	/**
	 * Method for Making a Reservation
	 * @param flight Flight Object
	 * @param name Name
	 * @param citizenship Citizenship
	 * @return Reservation
	 * @throws InvalidCitizenshipException  throws if Citizenship field is null or empty
	 * @throws NoMoreSeatsException throws if there are no more available seats on a selected flight
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws InvalidCitizenshipException, NoMoreSeatsException {
		String reserveCode = generateReservationCode(flight);
		if(citizenship == null || citizenship.equals("")) {
			throw new InvalidCitizenshipException();
		}
		if(getAvailableSeats(flight)==0) {
			throw new NoMoreSeatsException(flight.getCode());
		}
		
		
		Reservation r = new Reservation(reserveCode, flight.getCode(), flight.getAirlineName(), name, citizenship,
				flight.getCostPerSeat(), true);
		reservations.add(r);
		return r;
	}

	/**
	 * Method used for finding a Created Reservation
	 * @param code Code
	 * @param airline Airline
	 * @param name Name
	 * @return List of matched Reservations
	 */
	public ArrayList<Reservation> findReservation(String code, String airline, String name) {
		ArrayList<Reservation> myReservations = new ArrayList<>();
		for (Reservation r : reservations) {
			if (r.getCode().equalsIgnoreCase(code) || r.getAirline().equalsIgnoreCase(airline) || r.getName().contains(name)) {
				myReservations.add(r);
			}
		}
		return myReservations;
	}

	/**
	 * Method for finding a Created Reservation with reservation code
	 * @param code Code
	 * @return Reservation
	 */
	public Reservation findReservationByCode(String code) {
		Reservation myReso = null;
		for (Reservation r : reservations) {
			if (r.getCode().equals(code)) {
				myReso = r;
			}
		}
		return myReso;
	}

	/**
	 * Method used to Save Reservations
	 */
	public void persist() {
		try {
			RandomAccessFile raf = new RandomAccessFile(RESERVATIONS_BINARY, "rw");
			raf.setLength(0);

			for (Reservation r : this.reservations) {
				raf.writeUTF(r.getCode()); // 9 bytes
				raf.writeUTF(r.getFlightCode()); //7
				raf.writeUTF(String.format("%-25s", r.getAirline())); //27
				raf.writeUTF(String.format("%-50s", r.getName())); //52
				raf.writeUTF(String.format("%-50s", r.getCitizenship())); //52
				raf.writeDouble(r.getCost()); //8 bytes
				raf.writeBoolean(r.isActive()); //1 byte
			}
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to calculated a Flights available Seats
	 * @param flight Flight
	 * @return seatsAvailble
	 * @throws NoMoreSeatsException Throws exception is seatsAvailable equals 0
	 */
	public int getAvailableSeats(Flight flight) throws NoMoreSeatsException {
		int seatsTaken = 0;
		for (Reservation reservation : reservations)
			if (reservation.getFlightCode().equalsIgnoreCase(flight.getCode()) && reservation.isActive()) {
				seatsTaken++;
			}
		 int seatsAvailable = flight.getSeats() - seatsTaken;
		 
		 if (seatsAvailable==0){
				throw new NoMoreSeatsException(flight.getCode());
			}
		return seatsAvailable;
	}

	/**
	 * Method used to generate a Reservation Code
	 * @param flight Flight
	 * @return code
	 */
	public String generateReservationCode(Flight flight) {
		int max = 9999;
		int min = 1000;
		int digits = (int) (Math.random() * (max - min + 1)) + min;

		if (flight.isDomestic()) {
			return "D" + digits;
		} else
			return "I" + digits;
	}

	/**
	 * Method used to populate Reservation ArrayList upon Starting the program
	 * @throws IOException Throws IO Exception
	 */
	public void populateFromBinary() throws IOException  {
		reservations.clear();
		
		RandomAccessFile raf = new RandomAccessFile(RESERVATIONS_BINARY, "rw");
			
		for(long pos=0; pos < raf.length(); pos += RESO_SIZE) {
			reservations.add(new Reservation(raf.readUTF(), raf.readUTF(), raf.readUTF().trim(), raf.readUTF().trim(), raf.readUTF().trim(), raf.readDouble(), raf.readBoolean()));
		}
		raf.close();
	}
}
