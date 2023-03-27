package gestion_reservation_vol;

public class Compagnie {

	private String nom;
	
	
	
	public Compagnie() {}
	
	public Compagnie(String nom) {
		
		this.nom=nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
