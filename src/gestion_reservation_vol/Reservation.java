package gestion_reservation_vol;

import java.util.Date;

public class Reservation {

	private String numReservation;
	private Date dateReservation;
	private String sieges;
	public enum Etat{confirmée,annulée};
	public Client client ;
	public Vol vol ; 
	
	public Reservation() {}
	
	public Reservation(String numReservation, Date dateReservation, String sieges) {
		
		this.numReservation = numReservation;
		this.dateReservation=dateReservation;
		this.sieges=sieges;
	}

	public String getNumReservation() {
		return numReservation;
	}

	public void setNumReservation(String numReservation) {
		this.numReservation = numReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getSieges() {
		return sieges;
	}

	public void setSieges(String sieges) {
		this.sieges = sieges;
	}
}