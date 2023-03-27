package gestion_reservation_vol;

import java.util.Date;

public class Passager {

	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String numTelephone;
	
	
	public Passager() {}
	
	public Passager(String nom, String prenom, Date dateNaissance, String numTelephone) {
		
		this.nom=nom;
		this.prenom=prenom;
		this.dateNaissance=dateNaissance;
		this.numTelephone=numTelephone;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
}
