package gestion_reservation_vol;
import java.time.LocalTime;
import java.util.*;

public class Vol {
	
	private String numVol;
	private Date dateDep;
	private Date dateArrive;
	private LocalTime heureDep;
	private LocalTime heureArrive;
	public Compagnie compagnie;
	public Aeroport aeroport;
	
	public Vol() {}
	
	public Vol(Date dateArrive, String numVol, Date dateDep, LocalTime heureDep, LocalTime heureArrive) {
		
		this.dateArrive = dateArrive;
		this.numVol = numVol;
		this.dateDep = dateDep;
		this.heureDep = heureDep;
		this.heureArrive = heureArrive;
	}
	
	public Date getDateArrive() {
		return dateArrive;
	}
	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
	}
	
	
	public String getNumVol() {
		return numVol;
	}
	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}
	
	
	public Date getDateDep() {
		return dateDep;
	}
	public void setDateDep(Date dateDep) {
		this.dateDep = dateDep;
	}
	
	
	public LocalTime getHeureDep() {
		return heureDep;
	}
	public void setHeureDep(LocalTime heureDep) {
		this.heureDep = heureDep;
	}
	
	
	public LocalTime getHeureArrive() {
		return heureArrive;
	}
	public void setHeureArrive(LocalTime heureArrive) {
		this.heureArrive = heureArrive;
	}
}
