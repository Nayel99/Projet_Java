package projet_long_virus;

import java.util.ArrayList;

public class Pays {
	
	private String nom;
	private double NbSains;
	private double NbInfectes = 0;
	private double NbRetablis = 0;
	private double NbIncubation = 0;
	private double PopTotal;
	private double NbMort = 0;
	private ArrayList<Pays> Voisins;
	
	//Constructeur
	public Pays(String nom, int PopTotal) {
		this.setNom(nom);
		this.PopTotal = PopTotal;
		this.NbSains = PopTotal;
	}
	
	//Getter et Setter
	public double getNbSains() {
		return NbSains;
	}
	public void setNbSains(double nbSains) {
		NbSains = nbSains;
	}
	public double getNbInfectes() {
		return NbInfectes;
	}
	public void setNbInfectes(double nbInfectes) {
		NbInfectes = nbInfectes;
	}
	public double getNbRetablis() {
		return NbRetablis;
	}
	public void setNbRetablis(double nbRetablis) {
		NbRetablis = nbRetablis;
	}
	public double getNbIncubation() {
		return NbIncubation;
	}
	public void setNbIncubation(double nbIncubation) {
		NbIncubation = nbIncubation;
	}
	public double getPopTotal() {
		return PopTotal;
	}
	public void setPopTotal(double popTotal) {
		PopTotal = popTotal;
	}
	public double getNbMort() {
		return NbMort;
	}
	public void setNbMort(double nbMort) {
		NbMort = nbMort;
	}
	public ArrayList<Pays> getVoisins() {
		return Voisins;
	}
	public void setVoisins(ArrayList<Pays> voisins) {
		Voisins = voisins;
	}
	public Pays getVoisin_i(int Voisin_i) {
		return Voisins.get(Voisin_i);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
