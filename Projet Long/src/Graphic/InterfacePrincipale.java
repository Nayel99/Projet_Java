package Graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class InterfacePrincipale extends JFrame{

	private static final long serialVersionUID = -714093729841498863L;
	
	private static boolean VirusExiste = false;
	
	private static boolean TempsExiste = false;
	
	private static boolean SimulationPossible = false;
	
	static int TempsSimulation = 0;

	private JButton BoutonChoixVirus = new JButton("Choisir le virus");
	
	private JButton BoutonImpoterVirus = new JButton("Importer le virus");
	
	private JButton BoutonImpoterDate = new JButton("Ajuster la date");
	
	private JButton BoutonQuitter = new JButton("Quitter");
	
	private JButton BoutonLancerSimul = new JButton("Lancer la Simulation");
	
	private JComboBox<String> ChoixSimul;
	
	private JLabel text = new JLabel("Veullez choisir la simulation");
	
	private final ActionListener actionLancerSimul = new actionLancerSimul();
	
	private final ActionListener actionQuitter = new actionQuitter();
	
	private final ActionListener actionChoixVirus = new actionChoixVirus();

	private final ActionListener actionChoixTemps = new actionChoixTemps();

	public InterfacePrincipale() throws Exception {
		
		super( "SIMULATION VIRUS" );
		
		InitialiserFenetre();

		JPanel nord = AjouterNord();

		JPanel droite = AjouterDroite();
		
		EditerChoix();
		
		Positionner(nord, droite);

		AjouterTous(nord, droite);
	}
	
	private void Positionner(JPanel nord, JPanel droite) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		nord.setBounds(10, 10, xSize-20, 50);
		droite.setBounds(xSize-370, ySize-300, 300, 200);
		this.text.setBounds(65,  ySize-290, 300, 30);
		this.ChoixSimul.setBounds(20,  ySize-250, 300, 30);
	}
	
	private void EditerChoix() {
		this.text.setForeground(Color.white);
		this.text.setFont(new Font("Arial",Font.BOLD,14));
		this.text.setOpaque(false);
		String[] typeSim= { "type 1", "type 2", "type 3"};
		this.ChoixSimul = new JComboBox<String>(typeSim);
	}	
	
	private JLabel CreerBackground() {
		JLabel background;
		ImageIcon img = new ImageIcon("image/background.jpg");		
		background = new JLabel("",img,JLabel.CENTER);		
		background.setOpaque(false);
		return background;
		

	}
	
	private class actionChoixVirus implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				InterfaceChoixVirus Mafenetre;
				Mafenetre = new InterfaceChoixVirus();
				Mafenetre.setVisible(true);
		}
	}

	private class actionChoixTemps implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				InterfaceChoixTemps Mafenetre;
				Mafenetre = new InterfaceChoixTemps();
				Mafenetre.setVisible(true);
		}
	}
	
	private class actionLancerSimul implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (SimulationPossible == false) {
				JOptionPane.showMessageDialog(null, "Veuillez compléter votre choix !");
			}
			else {
				try {
					InterfaceSimulation Mafenetre;
					Mafenetre = new InterfaceSimulation();
					Mafenetre.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
			}
		}
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
		quitter.addActionListener(this.actionQuitter);
		menu_virus.add(quitter);
		
		menu.add(menu_virus);
		return menu;
	}
	
	private JPanel AjouterNord() {
		JPanel nord = new JPanel();

		nord.setLayout( new GridLayout(1, 3, 200,0) );
		
		BoutonChoixVirus.addActionListener(this.actionChoixVirus);
		nord.add(BoutonChoixVirus);
		
		nord.add(BoutonImpoterVirus);
		
		BoutonImpoterDate.addActionListener(this.actionChoixTemps);
		nord.add(BoutonImpoterDate);
		nord.setOpaque(false);
		
		return nord;
	}
	
	private JPanel AjouterDroite() {
		JPanel droite = new JPanel();
		droite.setLayout( new GridLayout(2, 1, 0,50) );
		droite.setOpaque(false);
		BoutonLancerSimul.addActionListener(this.actionLancerSimul);
		droite.add(BoutonLancerSimul);
		BoutonQuitter.addActionListener(this.actionQuitter);
		droite.add(BoutonQuitter);
		
		return droite;
	}
	
	private void AjouterTous(JPanel nord, JPanel droite) {
		this.setJMenuBar(CreerMenu());
		this.add(droite);
		this.add(nord);
		this.add(this.text);
		this.add(this.ChoixSimul);
		this.add(CreerBackground());
	}

	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout( new BorderLayout() );
	}
	
	private class actionQuitter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Fin !");
			System.exit(0);
		}
	}
	
	public static void SetTemps(int nb) {
		TempsSimulation = nb;
	}
	
	public static void VirusExiste(boolean b) {
		VirusExiste = b;
	}
	
	public static void TempsExiste(boolean b) {
		TempsExiste = b;
	}
	
	public static void Simulasionpossible() {
		if (VirusExiste == true && TempsExiste == true) {
			SimulationPossible = true;
		} else {
			SimulationPossible = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		UIManager.setLookAndFeel( new NimbusLookAndFeel() );	
		InterfacePrincipale Mafenetre = new InterfacePrincipale();		
		Mafenetre.setVisible(true);

	}
}

