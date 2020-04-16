package Graphic;

import java.awt.Color;
import java.awt.Graphics;

public class Cercle {

	private Point centre;
	private int rayon;
	private Color couleur; 
	
	public Cercle(Point centre, int rayon, Color couleur) {
		this.centre = centre;
		this.rayon = rayon;
		this.couleur = couleur;
	}
	
	public void DessinerCercle(Graphics g) {
		g.setColor(this.couleur);
		g.drawArc((int) this.centre.getX(), (int) this.centre.getY(), this.rayon, this.rayon, 0, 360);
		g.fillArc((int) this.centre.getX(), (int) this.centre.getY(), this.rayon, this.rayon, 0, 360);
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
