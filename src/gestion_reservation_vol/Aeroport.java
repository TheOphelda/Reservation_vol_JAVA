package gestion_reservation_vol;

public class Aeroport {


	private String nom;
	private String Adresse;
	
	
	public Aeroport() {}
	
	public Aeroport(String Adresse, String nom) {
		
		this.nom=nom;
		this.Adresse=Adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

}
