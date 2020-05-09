package projet_long_virus;

import java.util.ArrayList;

public class Continent {
	
	private ArrayList<Pays> Continent = new ArrayList<Pays>();
	
	private String nom;
	
	public Continent(String n) {
		this.nom = n;
	}

	// ajouter un pays au continent
	public void ajouterPays(Pays p) {
		this.Continent.add(p);
	}
	
	// enlever un pays d'un continent
	public void supprimerPays(Pays p) {
		this.Continent.remove(p);
	}
	
	public int getSize() {
		return Continent.size();
	}
	
	
	 // acceder a la liste des Pays du Continent
	 public ArrayList<Pays> getContinent() {
		 return this.Continent;
	 }
	 
	 public String getNom() {
		 return this.nom;
	 }
	 
	 public void setNom(String n) {
		 this.nom = n;
	 }

}
