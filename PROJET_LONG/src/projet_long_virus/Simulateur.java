package projet_long_virus;

public class Simulateur {
	
	private Virus virus;
	
	public Simulateur(Virus V) {
		this.virus = V;
	}

	public void simuler(Pays pays) {
		
		//Variables necessaires dans le pays
		int NbSains = pays.getNbSains();
		int NbInfectes = pays.getNbInfectes();
		int NbRetablis = pays.getNbRetablis();
		int NbIncubation = pays.getNbIncubation();
		int PopTotal = pays.getPopTotal();
		int NbMort = pays.getNbMort();
		
		//Variables necessaires du virus
		int T_Guerison = virus.getTempsGuerison();
		int R0 = virus.getR0();
		int T_Incubation = virus.getTempsIncubation();
		double Taux_Mortalite = virus.getTauxMortalite();
		
		//Taux d'incidence
		double Beta = (double) NbInfectes * R0 / PopTotal;
		
		//S'il y a des inféctés dans le pays
		if (NbInfectes > 0) {
			
		//Calcul de l'étape suivante (J+1)
		int NbSainsNext = (int) (NbSains - Math.round(NbInfectes * NbSains * Beta));
		int NbIncubationNext = (int) (NbIncubation + Math.round(Beta * NbInfectes * NbSains - (double) NbIncubation / T_Incubation));
		int NbInfectesNext = (int) (NbInfectes + Math.round((double) NbIncubation / T_Incubation - NbInfectes / T_Guerison - NbInfectes * Taux_Mortalite));
		int NbRetablisNext = (int) (NbRetablis + Math.round((double)NbInfectes / T_Guerison));
		int NbMortNext = (int) (NbMort + Math.round(NbInfectes * Taux_Mortalite));
		int PopTotalNext = PopTotal - NbMort;
		
		//Modification des variables du pays
		pays.setNbSains(NbSainsNext);
		pays.setNbInfectes(NbInfectesNext);
		pays.setNbRetablis(NbRetablisNext);
		pays.setNbIncubation(NbIncubationNext);
		pays.setPopTotal(PopTotalNext);
		pays.setNbMort(NbMortNext);
		} 
		
		//S'il n'y a toujours pas d'inféctés dans le pays
		else {
			for (int pays_voisins_i = 0; pays_voisins_i < pays.getVoisins().size(); pays_voisins_i++) {
				if (pays.getVoisin_i(pays_voisins_i).getNbInfectes() / pays.getVoisin_i(pays_voisins_i).getPopTotal() > 0.25) {
					pays.setNbInfectes(1);
				}
			}
		}
		
	}
	

}
