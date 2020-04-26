package projet_long_virus;

import java.util.ArrayList;

public class Continent {
	
	ArrayList<Pays> Continent = new ArrayList<Pays>();
	//private Pays[] Continent;
	
	//Création des pays
	Pays France = new Pays("France", 60000000);
	Pays Belgique = new Pays("Belgique", 1000000);
	
	//Création des voisins
	ArrayList<Pays> Voisins_France = new ArrayList<Pays>();
	ArrayList<Pays> Voisins_Belgique = new ArrayList<Pays>();
	
	//Ajouter les voisins
	public void ajouterVoisins() {
		
		//France
		Voisins_France.add(Belgique);
		France.setVoisins(Voisins_France);
		
		//Belgique
		Voisins_Belgique.add(France);
		Belgique.setVoisins(Voisins_Belgique);
	}
	
	//Création du continant
	 public void creerContinent() {
		 Continent.add(France);
		 Continent.add(Belgique);
	 }
	 
	 //Pour accéder à la liste des Pays du Continent
	 public ArrayList<Pays> getPays() {
		 return Continent;
	 }

}
