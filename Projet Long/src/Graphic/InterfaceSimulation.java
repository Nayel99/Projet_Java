package Graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import projet_long_virus.Continent;
import projet_long_virus.Simulateur;
import projet_long_virus.Virus;
import projet_long_virus.Courbe.TraceurCourbes;



public class InterfaceSimulation extends JFrame{

	private static final long serialVersionUID = -714093729841498863L;

	private static Virus virus_Utilisateur = new Virus();

	private cercleTrace traceur = new cercleTrace();

	private JButton BoutonStart = new JButton("Start");

	private JButton BoutonPause = new JButton("pause");

	private JButton BoutonRetour = new JButton("Retour");

	private JButton BoutonQuitter = new JButton("Quitter");

	private final ActionListener actionStart = new actionStart();

	private final ActionListener actionPause = new actionPause();


	private final ActionListener actionQuitter = new actionQuitter();

	private final ActionListener actionRetour = new actionRetour();

	public InterfaceSimulation() throws Exception {

		super( "SIMULATION VIRUS" );

		InitialiserFenetre();

		JPanel container = (JPanel)  this.getContentPane();
		this.setJMenuBar(CreerMenu());

		container.setLocation(50, 50);


		container.setLayout(new BorderLayout());
		
		//container.add(CreerBackground(),BorderLayout.CENTER);
		this.add(traceur,BorderLayout.CENTER);
		traceur.add(CreerBackground());
		container.add(Bas(),BorderLayout.SOUTH);
		
	}

	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout( new BorderLayout() );
	}

	private JPanel Bas() {
		JPanel bas = new JPanel();
		bas.setLayout( new FlowLayout(FlowLayout.CENTER, 100,30 ) );
		bas.setOpaque(false);
		BoutonStart.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonStart);
		BoutonPause.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonPause);
		BoutonRetour.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonRetour);
		BoutonQuitter.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonQuitter);

		BoutonStart.addActionListener(this.actionStart); 

		BoutonPause.addActionListener(this.actionPause); 

		BoutonRetour.addActionListener(this.actionRetour); 

		BoutonQuitter.addActionListener(this.actionQuitter);

		return bas;
	}

	private JLabel CreerBackground() {
		JLabel background;
		ImageIcon img = new ImageIcon("image/wp2593836 copie.jpg");
		background = new JLabel(img);	
		background.setOpaque(false);
		return background;

	}

	private JMenuBar CreerMenu() {
		JMenuBar menu =  new JMenuBar();
		JMenu menu_virus =  new JMenu("Virus");
		JMenuItem nouvelle_partie = new JMenuItem("Nouvelle partie");
		JMenuItem lancerSim = new JMenuItem("Lancer la Simulation");
		JMenuItem ChoixVirus = new JMenuItem("Choix du virus");
		JMenuItem ImpoterVirus = new JMenuItem("Importer le virus");
		JMenuItem ImpoterDate = new JMenuItem("Importer la date");
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(this.actionQuitter);
		menu_virus.add(nouvelle_partie);
		menu_virus.add(lancerSim);
		menu_virus.add(ChoixVirus);
		menu_virus.add(ImpoterVirus);
		menu_virus.add(ImpoterDate);
		menu_virus.add(quitter);
		menu.add(menu_virus);
		return menu;
	}

	private class actionStart implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			ViderTabCercle();
			//Exemple();
			simulation();
		}			
	}

	private class actionPause implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// à faire
		}			
	}

	private class actionRetour implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ViderTabCercle();
			try {
				InterfacePrincipale Mafenetre;
				Mafenetre = new InterfacePrincipale();
				Mafenetre.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	private class actionQuitter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Fin !");
			System.exit(0);
		}
	}

	public static void SetVirus(Virus virus) {
		virus_Utilisateur.setR0(virus.getR0());
		virus_Utilisateur.setTauxMortalite(virus.getTauxMortalite());
		virus_Utilisateur.setTempsGuerison(virus.getTempsGuerison());
		virus_Utilisateur.setTempsIncubation(virus.getTempsIncubation());
	}

	public void simulation() {
		// A faire
		
		//Cr�ation du continent
		Continent Europe = new Continent();
		Europe.creerContinent();
		Europe.ajouterVoisins();

		virus_Utilisateur.setR0(4);
		virus_Utilisateur.setTempsGuerison(6);
		virus_Utilisateur.setTempsIncubation(2);
		virus_Utilisateur.setTauxMortalite(2);
		
		//Cr�ation du simulateur
		Simulateur simulateur = new Simulateur(virus_Utilisateur);

		//Infection du pays demand� par l'utilisateur
		Europe.France.setNbInfectes(10);
		Europe.France.setPopTotal(5999990);
		Europe.France.setNbSains(5999990);
		Europe.Belgique.setNbInfectes(5);
		Europe.Belgique.setPopTotal(699990);
		Europe.Belgique.setNbSains(6999990);
		
		//Cr�ation d'une courbe
		TraceurCourbes courbetraceur = new TraceurCourbes();
		IinitaliserListeCercle(Europe.getPays().size());
		//Simulation �tape par �tape avec le temps qui augmente � chaque fois qu'une �tape se termine
		int Jour_i = 1;
		while (Jour_i < 250) {

			//Simulation en interne des pays
			for (int pays_i=0; pays_i < Europe.getPays().size(); pays_i++) {

				//Simulation pour tous les pays du continent
				simulateur.simuler(Europe.getPays().get(pays_i));
				double nb = Europe.getPays().get(pays_i).getNbInfectes()/100;
				System.out.println((int) nb);
				traceur.modifierRayon(pays_i, (int) nb+10);
				traceur.acctualiser();
			}
			double nb = Europe.France.getNbInfectes();
			//System.out.println(nb);
			projet_long_virus.Courbe.Point p = new projet_long_virus.Courbe.Point(Jour_i, nb);
			
			courbetraceur.courbe.ajouterPoint(p);
			
			Jour_i = Jour_i + 1;

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void IinitaliserListeCercle(int j) {
	
		Random rndx =  new Random();

		for (int i=0; i < j; i++) {
			int x = rndx.nextInt(300) + 100;
			int y = rndx.nextInt(100) + 100;
			int r = 0;
			traceur.ajoutercercle(new Cercle(new Point(x,y), r, Color.red));
		}
	}

	private void ModifierRayon(int j) {
		Random rnd =  new Random();

		for (int i=0; i < j; i++) {
			int r = rnd.nextInt(3);
			traceur.modifierRayon(i, r);
		}
	}

	private void ViderTabCercle() {
		traceur.viderliste();
	}

	public void Exemple() {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		IinitaliserListeCercle(10);
		traceur.afficherListe();
		int j =0;
		while (j<20) {
			System.out.println(j);
			this.ModifierRayon(10);
			traceur.afficherListe();
			for (int i=0; i< 10; i++) {
				traceur.acctualiser();
			} 

			//this.courbe.courbe.ajouterPoint(new projet_long_virus.Courbe.Point(j*20, cercle.getrayon()));
			j = j+1;
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){

		try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
			InterfaceSimulation Mafenetre = new InterfaceSimulation();
			Mafenetre.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		//Mafenetre.Exemple();
	}


}


