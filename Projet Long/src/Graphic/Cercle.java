package Graphic;

import java.awt.Color;

public class Cercle{

	private Point centre;
	private int rayon;
	private Color couleur; 
	
	public Cercle(Point centre, int rayon, Color couleur) {
		this.centre = centre;
		this.rayon = rayon;
		this.couleur = couleur;
	}
	
	public Point getCentre() {
		return this.centre;
	}
	
	public Color getColor() {
		return this.couleur;
	}
	
	public int getrayon() {
		return this.rayon;
	}

	/** Changer l'abscisse du point.
	  * @param vx nouvelle abscisse
	  */
	public void setrayon(int rayon) {
		this.rayon = rayon;
	}
}
