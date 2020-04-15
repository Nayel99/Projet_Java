package projet_long_virus;

import java.util.ArrayList;

public class Pays {
	
	private String nom;
	private int NbSains;
	private int NbInfectes = 0;
	private int NbRetablis = 0;
	private int NbIncubation = 0;
	private int PopTotal;
	private int NbMort = 0;
	private ArrayList<Pays> Voisins;
	
	//Constructeur
	public Pays(String nom, int PopTotal) {
		this.setNom(nom);
		this.PopTotal = PopTotal;
		this.NbSains = PopTotal;
	}
	
	//Getter et Setter
	public int getNbSains() {
		return NbSains;
	}
	public void setNbSains(int nbSains) {
		NbSains = nbSains;
	}
	public int getNbInfectes() {
		return NbInfectes;
	}
	public void setNbInfectes(int nbInfectes) {
		NbInfectes = nbInfectes;
	}
	public int getNbRetablis() {
		return NbRetablis;
	}
	public void setNbRetablis(int nbRetablis) {
		NbRetablis = nbRetablis;
	}
	public int getNbIncubation() {
		return NbIncubation;
	}
	public void setNbIncubation(int nbIncubation) {
		NbIncubation = nbIncubation;
	}
	public int getPopTotal() {
		return PopTotal;
	}
	public void setPopTotal(int popTotal) {
		PopTotal = popTotal;
	}
	public int getNbMort() {
		return NbMort;
	}
	public void setNbMort(int nbMort) {
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
