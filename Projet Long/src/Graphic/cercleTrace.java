package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class cercleTrace extends JPanel{

	private static final long serialVersionUID = -2851411364295604193L;
	private Vector<Cercle> listeCercle=new Vector<Cercle>();

	public void acctualiser() {
		this.update(getGraphics());
		this.repaint();

	}

	public void ajoutercercle(Cercle c) {
		this.listeCercle.add(c);
	}

	public void viderliste() {
		this.listeCercle.clear();
	}

	public void modifierRayon(int i, int r) {
		this.listeCercle.get(i).setrayon(r);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (this.listeCercle.size() == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(50,50,50,50);
		} else {
			for(int i=0; i<this.listeCercle.size()-1; i++){
				Cercle c = this.listeCercle.get(i);
				g.setColor(Color.RED);
				if (c.getrayon() > 0) {
				g.drawArc((int) c.getCentre().getX(), (int) c.getCentre().getY(), c.getrayon(), c.getrayon(), 0, 360);
				g.fillArc((int) c.getCentre().getX(), (int) c.getCentre().getY(), c.getrayon(), c.getrayon(), 0, 360);
				}
			}
		}
	}

	public void afficherListe() {
		for(int i=0; i<this.listeCercle.size()-1; i++){
			Cercle c = this.listeCercle.get(i);
			System.out.println("centre : (" + c.getCentre().getX() + ", " + c.getCentre().getY() + 
					"), rayon : " + c.getrayon());
		}
	}

}
