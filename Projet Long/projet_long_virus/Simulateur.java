package projet_long_virus;

public class Simulateur {
	
	private Virus virus;
	
	public Simulateur(Virus V) {
		this.virus = V;
	}

	public void simuler(Pays pays) {
		
		//Variables necessaires dans le pays
		double NbSains = pays.getNbSains();
		double NbInfectes = pays.getNbInfectes();
		double NbRetablis = pays.getNbRetablis();
		double NbIncubation = pays.getNbIncubation();
		double PopTotal = pays.getPopTotal();
		double NbMort = pays.getNbMort();
		
		//Variables necessaires du virus
		int T_Guerison = virus.getTempsGuerison();
		int R0 = virus.getR0();
		int T_Incubation = virus.getTempsIncubation();
		double Taux_Mortalite = virus.getTauxMortalite();
		
		//Taux d'incidence
		double Beta = R0 / (PopTotal * T_Guerison);
		//S'il y a des infectes dans le pays
		if (NbInfectes > 0) {

		//Calcul de l'etape suivante (J+1)
		double NbSainsNext = NbSains - NbSains * NbInfectes * Beta;
		double NbIncubationNext = NbIncubation + NbSains * NbInfectes * Beta - NbIncubation / T_Incubation;
		double NbInfectesNext = NbInfectes + NbIncubation / T_Incubation - NbInfectes / T_Guerison - NbInfectes * Taux_Mortalite / T_Guerison;
		double NbRetablisNext = NbRetablis + NbInfectes / T_Guerison;
		double NbMortNext = NbMort + NbInfectes * Taux_Mortalite / T_Guerison;
		double PopTotalNext = PopTotal - NbInfectes * Taux_Mortalite / T_Guerison;
		
		
		//Modification des variables du pays
		pays.setNbSains(NbSainsNext);
		pays.setNbInfectes(NbInfectesNext);
		pays.setNbRetablis(NbRetablisNext);
		pays.setNbIncubation(NbIncubationNext);
		pays.setPopTotal(PopTotalNext);
		pays.setNbMort(NbMortNext);
		} 
		
		//S'il n'y a toujours pas d'infectes dans le pays
		
		/*
		else {
			for (int pays_voisins_i = 0; pays_voisins_i < pays.getVoisins().size(); pays_voisins_i++) {
				
				Pays voisin = pays.getVoisin_i(pays_voisins_i); // le pays voisin
				if (voisin.getNbInfectes() / voisin.getPopTotal() > 0.1) {
					pays.setNbInfectes(1);
				}
				
			}
		}*/
		
		
	}
	

}
