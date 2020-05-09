package Graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import projet_long_virus.Continent;
import projet_long_virus.Pays;
import projet_long_virus.Simulateur;
import projet_long_virus.Virus;



public class InterfaceSimulation extends JFrame implements Runnable, ActionListener {

	private static Virus virusUtilisateur;

	private static int NB_ITERATIONS = 100;

	private static final int TEMPS_SIMU = 20; // en millisecondes

	private Simulateur simulateur;

	private Continent continent;

	private static final long serialVersionUID = -714093729841498863L;

	private JCheckBox afficherCourbe = new JCheckBox("Afficher courbe");

	private boolean afficher = false;

	private cercleTrace traceur = new cercleTrace();

	private TraceurCourbes courbetraceur = new TraceurCourbes();


	// Ajout des statistiques
	private JLabel JLNbinefecte = new JLabel("Actuellement Infect�s :");

	private JTextField infecte = new JTextField();

	private JLabel JLNretablie = new JLabel("Total R�tablis :");

	private JTextField retablie = new JTextField();

	private JLabel JLNbMort = new JLabel("Total Morts :");

	private JTextField mort = new JTextField();

	private JLabel JLNbCas = new JLabel("Total Cas Confirm�s :");

	private JTextField casConfirmes = new JTextField();

	private final AbstractAction actionCheckbox = new actionCheckbox("check");


	private JButton BoutonStart = new JButton("Start");

	private JButton BoutonPause = new JButton("Pause");

	private JButton BoutonReset = new JButton("Reset");

	private JButton BoutonRetour = new JButton("Retour");

	private JButton BoutonQuitter = new JButton("Quitter");
    
	int Jour_i = 0;

	private boolean running = false;

	public InterfaceSimulation() throws Exception {

		super( "SIMULATION VIRUS" );

		this.simulateur = initialiserSimulateur();
		initialiserContinent();
		initialiserTraceur();

		InitialiserFenetre();

		JPanel container = (JPanel)  this.getContentPane();
		this.setJMenuBar(CreerMenu());

		container.setLocation(50, 50);
		container.setLayout(new BorderLayout());

		//container.add(CreerBackground(),BorderLayout.CENTER);
		this.add(traceur,BorderLayout.CENTER);
		traceur.add(CreerCarte());
		container.add(Bas(),BorderLayout.SOUTH);

	}

	public static void InitialiserVirus(Virus virus) {
		virusUtilisateur = new Virus(virus.getTempsGuerison(), virus.getR0(), virus.getTempsIncubation(), virus.getTauxMortalite());
	}

	public static void InitialiserTemps(int nb) {
		InterfaceSimulation.NB_ITERATIONS = nb;
	}

	private Simulateur initialiserSimulateur() {
		//virusUtilisateur = new Virus(10, 20, 3, 3);   // Normalement il faut recuperer les valeurs entrees
		return new Simulateur(InterfaceSimulation.virusUtilisateur);           // par l'utilisateur
	}

	private void initialiserContinent() {
		this.continent = new Continent("Europe");
		Pays USA = new Pays("USA", 300000000, 265, 260);
		Pays France = new Pays("France", 65000000, 550, 225);
		Pays Maroc = new Pays("Maroc", 30000000, 515, 290);
		Pays Chine = new Pays("Chine", 1300000000, 820, 300);
		this.continent.ajouterPays(France);
		this.continent.ajouterPays(USA);
		this.continent.ajouterPays(Maroc);
		this.continent.ajouterPays(Chine);

		for(Pays p : this.continent.getContinent()) {
			p.setNbInfectes(1);
		}

	}

	// Apres un reset, on remet le nb_infecte a 1 dans tous les pays du continent
	private void initialiserPays() {
		for(Pays p: this.continent.getContinent()) {
			p.setNbInfectes(3);
			p.setNbMort(0);
			p.setNbRetablis(0);
			p.setNbSains(p.getPopTotal()-1);
			p.setNbIncubation(0);
		}
	}

	private void initialiserTraceur() {
		int n = this.continent.getSize();
		Pays p;
		int x, y;
		for(int i=0; i<n; i++) {
			// recuperer les coordonnes des pays sur la map
			p = this.continent.getContinent().get(i);
			x = p.getX();
			y = p.getY();
			this.traceur.ajoutercercle(new Cercle(new Point(x,y), 0, Color.red));
		}

	}

	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setLayout( new BorderLayout() );
	}

	private JPanel Bas() {
		JPanel bas = new JPanel(new BorderLayout());
		bas.add(Boutton(), BorderLayout.CENTER);
		bas.add(Statistic(), BorderLayout.SOUTH);
		return bas;
	}

	private JPanel Boutton() {
		JPanel Boutton = new JPanel();
		Boutton.setLayout( new FlowLayout(FlowLayout.CENTER, 100,10 ) );
		Boutton.setOpaque(false);

		afficherCourbe.setPreferredSize(new Dimension(120,30));
		Boutton.add(afficherCourbe);
		afficherCourbe.addActionListener(actionCheckbox);


		BoutonStart.setPreferredSize(new Dimension(100,30));
		Boutton.add(BoutonStart);
		BoutonStart.addActionListener(this);

		BoutonPause.setPreferredSize(new Dimension(100,30));
		Boutton.add(BoutonPause);
		BoutonPause.addActionListener(this);

		BoutonReset.addActionListener(this);

		BoutonRetour.setPreferredSize(new Dimension(100,30));
		Boutton.add(BoutonRetour);
		BoutonRetour.addActionListener(this);

		BoutonQuitter.setPreferredSize(new Dimension(100,30));
		Boutton.add(BoutonQuitter);
		BoutonQuitter.addActionListener(this);

		return Boutton;
	}

	private JPanel Statistic() {
		JPanel Statistic = new JPanel();
		Statistic.setLayout( new FlowLayout(FlowLayout.CENTER, 70,10 ));
		Statistic.setOpaque(true);
		Statistic.add(JLNbinefecte);
		Statistic.add(CreerMessage(JLNbinefecte, infecte));
		Statistic.add(CreerMessage(JLNretablie, retablie));
		Statistic.add(CreerMessage(JLNbMort, mort));
		Statistic.add(CreerMessage(JLNbCas, casConfirmes));

		return Statistic;
	}

	private JPanel CreerMessage(JLabel label, JTextField LeMessage) {
		JPanel dialogue = new JPanel(new FlowLayout());
		dialogue.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10 ));
		dialogue.add(label);
		label.setPreferredSize(new Dimension(100,30));
		LeMessage.setEditable(false);
		dialogue.add(LeMessage);
		LeMessage.setPreferredSize(new Dimension(100,30));
		return dialogue;
	}

	private JLabel CreerCarte() {
		JLabel Carte;
		ImageIcon img = new ImageIcon("image/carte.jpg");
		Carte = new JLabel(img);
		Carte.setOpaque(false);
		return Carte;
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

		menu_virus.add(nouvelle_partie);
		menu_virus.add(lancerSim);
		menu_virus.add(ChoixVirus);
		menu_virus.add(ImpoterVirus);
		menu_virus.add(ImpoterDate);
		menu_virus.add(quitter);
		menu.add(menu_virus);
		return menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(BoutonStart)) {
			if (!running) {
				BoutonStart.setText("Start");
				BoutonPause.setText("Pause");
				courbetraceur.dispose();
				courbetraceur.setVisible(afficher);
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		else if (e.getSource().equals(BoutonQuitter)) {
			running = false;
			int reponse = JOptionPane.showConfirmDialog(this,
					"Voulez-vous vraiment quitter la simulation ?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(reponse == JOptionPane.YES_OPTION ){
				JOptionPane.showMessageDialog(null, "Fin !");
				System.exit(0);
			}
		} else if (e.getSource().equals(BoutonRetour)) {
			running = false;
			int reponse = JOptionPane.showConfirmDialog(this,
					"Voulez-vous vraiment retourner ?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(reponse == JOptionPane.YES_OPTION ){
				try {
					courbetraceur.dispose();
					this.dispose();
					InterfacePrincipale Mafenetre;
					Mafenetre = new InterfacePrincipale();
					Mafenetre.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}


		}

		else if (e.getSource().equals(BoutonPause)) {
			if(running) {
				running = false;
				BoutonStart.setText("Resume");
				BoutonPause.setText("Reset");
			} else {
                // Il s'agit de reset
				int reponse = JOptionPane.showConfirmDialog(this,
						"Voulez-vous vraiment redemarrer la simulation ?",
						"Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				BoutonStart.setText("Resume");
				if(reponse == JOptionPane.YES_OPTION ){

					BoutonStart.setText("Start");
					infecte.setText(Integer.toString(0));
					retablie.setText(Integer.toString(0));
					mort.setText(Integer.toString(0));
					casConfirmes.setText(Integer.toString(0));

					courbetraceur.dispose();
					courbetraceur = new TraceurCourbes();
					this.Jour_i = 0;
					initialiserPays();
					this.traceur.viderliste();
					initialiserTraceur();
					this.traceur.actualiser(); 
				}

			}
		}
		/*else if (e.getSource().equals(BoutonReset)) {
			running = false;
			int reponse = JOptionPane.showConfirmDialog(this,
	                "Voulez-vous vraiment redemarrer la simulation ?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);
			BoutonStart.setText("Resume");
			if(reponse == JOptionPane.YES_OPTION ){

				BoutonStart.setText("Start");
				infecte.setText(Integer.toString(0));
				retablie.setText(Integer.toString(0));
				mort.setText(Integer.toString(0));
				casConfirmes.setText(Integer.toString(0));

				courbetraceur.dispose();
				courbetraceur = new TraceurCourbes();
				this.Jour_i = 0;
				initialiserPays();
				this.traceur.viderliste();
				initialiserTraceur();
				this.traceur.actualiser(); 

			}

			/*try {
				InterfacePrincipale Mafenetre;
				Mafenetre = new InterfacePrincipale();
				Mafenetre.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}*/
	}


	public class actionCheckbox extends AbstractAction {
		private static final long serialVersionUID = -8431132970058967602L;

		public actionCheckbox(String text) {
			super(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox cbLog = (JCheckBox) e.getSource();
			if (cbLog.isSelected()) {
				afficher = true;
				if(afficher) {
				}
			} else {
				afficher = false;
			}
		}
	}

	// Calculer l'etape suivante
	public void step() {
        Integer infectes = 0;
        Integer retablis = 0;
        Integer morts = 0;
		// Calculer l'etape suivante de la simulation
		for(int i = 0; i < this.continent.getSize(); i++) {
			Pays p = this.continent.getContinent().get(i); // recuperer le pays en position i
			this.simulateur.simuler(p); //simuler le pays en position i 
			this.traceur.modifierRayon(i, (int) Math.log10(p.getNbInfectes() /*+ p.getNbMort() + p.getNbRetablis()*/)*10); //modifier la valeur du rayon du cercle dans le traceur
			// Calculer le nombre total de chaque parametre
			infectes = infectes + (int) p.getNbInfectes();
			retablis = retablis + (int) p.getNbRetablis();
			morts = morts + (int) p.getNbMort();
		}

		// modifier l'affichage
		infecte.setText(Integer.toString(infectes));
		retablie.setText(Integer.toString(retablis));
		mort.setText(Integer.toString(morts));
		casConfirmes.setText(Integer.toString(infectes + retablis + morts));
	}

	public void run() {
		while (running && Jour_i < InterfaceSimulation.NB_ITERATIONS) {
			step();
			traceur.actualiser();
			//traceur.afficherListe();
			courbetraceur.courbe.ajouterPoint(new Point(Jour_i, this.continent.getContinent().get(1).getNbInfectes()));
			courbetraceur.setVisible(afficher);
			if(afficher) {
				courbetraceur.courbe.acctualiser();
			}
			try {
				Thread.sleep(TEMPS_SIMU);
			} catch (Exception e){
				e.printStackTrace();
			}
			Jour_i ++;
            if (Jour_i == InterfaceSimulation.NB_ITERATIONS) {
                JOptionPane.showMessageDialog(null, "Fin de la simulation!");
            }
		}
	}
}


