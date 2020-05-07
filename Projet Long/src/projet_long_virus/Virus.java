package projet_long_virus;

public class Virus {
	
	private int T_Guerison;
	private int R0;
	private int T_Incubation;
	private float Taux_Mortalite;
	
	public int getTempsGuerison() {
		return T_Guerison;
	}
	public void setTempsGuerison(int t_Guerison) {
		T_Guerison = t_Guerison;
	}
	public int getR0() {
		return R0;
	}
	public void setR0(int r0) {
		R0 = r0;
	}
	public int getTempsIncubation() {
		return T_Incubation;
	}
	public void setTempsIncubation(int t_Incubation) {
		T_Incubation = t_Incubation;
	}
	public float getTauxMortalite() {
		return Taux_Mortalite;
	}
	public void setTauxMortalite(float mortalite_Virus_Utilisateur) {
		Taux_Mortalite = mortalite_Virus_Utilisateur;
	}

}
