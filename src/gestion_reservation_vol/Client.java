package gestion_reservation_vol;

public class Client {

	private String Adresse;
	
	
	
	public Client() {}
	
	public Client(String Adresse) {
		
		this.Adresse=Adresse;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
}
