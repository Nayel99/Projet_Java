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

public class InterfaceSimulation extends JFrame{

	private static final long serialVersionUID = -714093729841498863L;
		
	private Cercle[] TabCercle = new Cercle[0];

	private Cercle cercle;
	
	private JButton BoutonStart = new JButton("Start");
	
	//private JButton BoutonPause = new JButton("pause");
	
	private JButton BoutonRetour = new JButton("Retour");
	
	private JButton BoutonQuitter = new JButton("Quitter");
	
	private final ActionListener actionStart = new actionStart();
	
	private final ActionListener actionQuitter = new actionQuitter();
	
	private final ActionListener actionRetour = new actionRetour();

	
	public InterfaceSimulation() throws Exception {
		
		super( "SIMULATION VIRUS" );

		InitialiserFenetre();

		
		this.setJMenuBar(CreerMenu());
		
		this.add(Bas(), BorderLayout.SOUTH);

		this.add(CreerBackground(),BorderLayout.NORTH);
		
	}
	
	
	
		
	private JPanel Bas() {
		JPanel bas = new JPanel();
		bas.setLayout( new FlowLayout(FlowLayout.CENTER, 100,30 ) );
		bas.setOpaque(false);
		BoutonStart.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonStart);
		BoutonRetour.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonRetour);
		BoutonQuitter.setPreferredSize(new Dimension(100,30));
		bas.add(BoutonQuitter);
		
		BoutonStart.addActionListener(this.actionStart); 
		
		BoutonRetour.addActionListener(this.actionRetour); 
		
		BoutonQuitter.addActionListener(this.actionQuitter);
		
		return bas;
	}
	
	private void IinitaliserTabCercle() {
		this.TabCercle = new Cercle[10];
		Random rndx =  new Random();

		for (int i=0; i < 10; i++) {
			int x = rndx.nextInt(300) + 600;
			int y = rndx.nextInt(100) + 100;
			int r = rndx.nextInt(3);
			this.TabCercle[i] = new Cercle(new Point(x, y), r, Color.RED);	
		}
	}
	
	private void ModifierRayon() {
		Random rnd =  new Random();

		for (int i=0; i < 10; i++) {
			int r = rnd.nextInt(2);
			this.TabCercle[i].setrayon(this.TabCercle[i].getrayon()+r);	
		}
	}
	
	
	private JLabel CreerBackground() {
		JLabel background;
		ImageIcon img = new ImageIcon("image/wp2593836 copie.jpg");		
		background = new JLabel(img);		
		background.setOpaque(false);
		return background;
		
	}
	
	private void ViderTabCercle() {
		if (this.TabCercle.length != 0) {
			this.TabCercle = new Cercle[0];
		}
	}
	
	private class actionStart implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ViderTabCercle();
			Exemple();
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
	
	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLayout( new BorderLayout() );
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
	
	public void Exemple() {
		super.paint(getGraphics());
		try {
			 Thread.sleep(800);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
  		}
		this.IinitaliserTabCercle();
		for (int j=0; j<10; j++) {
			this.ModifierRayon();

		        for (int i=0; i< ( this.TabCercle ).length; i++) {
		 		   this.cercle = this.TabCercle[i];
		 		   this.cercle.DessinerCercle(getGraphics());
		        } 
		    
			 try {
				 Thread.sleep(400);
				 } catch (InterruptedException e) {
					 e.printStackTrace();
	   		}
		 }
	}
	
	public static void main(String[] args) throws Exception {
		
		UIManager.setLookAndFeel( new NimbusLookAndFeel() );	
		InterfaceSimulation Mafenetre = new InterfaceSimulation();		
		Mafenetre.setVisible(true);
		//Mafenetre.Exemple();
	}

}


