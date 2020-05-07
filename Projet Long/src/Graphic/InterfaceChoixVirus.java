package Graphic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import projet_long_virus.Virus;

public class InterfaceChoixVirus extends JFrame{

	private static final long serialVersionUID = -3606889020291861942L;
	
	private JLabel JLnomVirus = new JLabel("Nom du virus :");
	
	private JLabel JLPersonneInfécté = new JLabel("Taux de Contamination :");
	
	private JLabel JLTauxMortalité = new JLabel("Taux de mortalité :");
	
	private JLabel JLGuerison = new JLabel("Temps de Guerison :");
	
	private JLabel JLIncubation = new JLabel("Taux d'incubation :");
		
	private JTextField MessageNom = new JTextField();
	
	private JTextField MessageContamination = new JTextField();
	
	private JTextField MessageMortalite = new JTextField();

	private JTextField MessageGuerison = new JTextField();
	
	private JTextField MessageIncubation = new JTextField();
		
	private final ActionListener actionNomOK = new actionNomOK();
	
	private final ActionListener actionContaminationOK = new actionContaminationOK();
	
	private final ActionListener actionMortaliteOK = new actionMortaliteOK();

	private final ActionListener actionGuerisonOK = new actionGuerisonOK();
	
	private final ActionListener actionIncubationOK = new actionIncubationOK();
		
	private  JButton BouttonOk = new JButton("Ok");
	
	private final ActionListener actionOK = new actionOK();
	
	private  JButton BouttonAnnuler = new JButton("Annuler");
	
	private final ActionListener actionAnnuler = new actionAnnuler();
	
	private Virus virus_Utilisateur = new Virus();
	
	public InterfaceChoixVirus() {
		super( "Choisir le virus" );
		
		InitialiserFenetre();
		AjouterListener();
		this.add(new JScrollPane(AjouterTous()),BorderLayout.CENTER);
		this.add(sud(),BorderLayout.SOUTH);
	}
	
	private void AjouterListener() {

		this.MessageNom.addActionListener(this.actionNomOK);
		
		this.MessageContamination.addActionListener(this.actionContaminationOK);
		
		this.MessageMortalite.addActionListener(this.actionMortaliteOK);
		
		this.MessageGuerison.addActionListener(this.actionGuerisonOK);
		
		this.MessageIncubation.addActionListener(this.actionIncubationOK);
				
		this.BouttonOk.addActionListener(this.actionOK);
		
		this.BouttonAnnuler.addActionListener(this.actionAnnuler);
	}
	
 	private JPanel sud() {
		JPanel dialogue = new JPanel(new FlowLayout());
		dialogue.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10 ));
		dialogue.add(BouttonOk);
		BouttonOk.setPreferredSize(new Dimension(100,30));
		dialogue.add(BouttonAnnuler);
		BouttonAnnuler.setPreferredSize(new Dimension(100,30));
		return dialogue;
	}
	
	private JPanel AjouterTous() {
		JPanel d = new JPanel(new FlowLayout());
		d.setLayout(new GridLayout(5,1,0,20));
		d.add(CreerMessage(JLnomVirus, MessageNom));
		d.add(CreerMessage(JLPersonneInfécté, MessageContamination));
		d.add(CreerMessage(JLTauxMortalité, MessageMortalite));		
		d.add(CreerMessage(JLGuerison, MessageGuerison));
		d.add(CreerMessage(JLIncubation, MessageIncubation));
		return d;
	}
	
	private JPanel CreerMessage(JLabel label, JTextField LeMessage) {
		JPanel dialogue = new JPanel(new FlowLayout());
		dialogue.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10 ));
		dialogue.add(label);
		label.setPreferredSize(new Dimension(150,30));
		dialogue.add(LeMessage);
		LeMessage.setPreferredSize(new Dimension(70,30));
		return dialogue;
	}
	
	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize( 400, 300 );																			
		this.setLocationRelativeTo( null ); 
		this.setLayout( new BorderLayout() );
	}
	
	public static void main(String[] args)  {
		try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}	
		InterfaceChoixVirus Mafenetre = new InterfaceChoixVirus();		
		Mafenetre.setVisible(true);

	}	
	
	public void actionNomOK() {
		
	}
	
	public void actionContamination() {
		virus_Utilisateur.setR0(Integer.parseInt(MessageContamination.getText()));
	}
	
	public void actionMortalite() {
		virus_Utilisateur.setTauxMortalite(Float.parseFloat(MessageMortalite.getText()));
		
	}
	
	public void actionGuerison() {
		virus_Utilisateur.setTempsGuerison(Integer.parseInt(MessageGuerison.getText()));  	
	}

	public void actionIncubation(){
		virus_Utilisateur.setTempsIncubation(Integer.parseInt(MessageIncubation.getText()));
	}
	
	public void actionOk() {
		if (MessageNom.getText() != " " 
				&& MessageContamination.getText().length() != 0 
				&& MessageGuerison.getText().length() != 0
				&& MessageIncubation.getText().length() != 0
				&& MessageMortalite.getText().length() != 0) {
			actionNomOK();
			actionContamination();
			actionMortalite();
			actionGuerison();
			actionIncubation();
			InterfacePrincipale.VirusExiste(true);
			InterfacePrincipale.Simulasionpossible();
			InterfaceSimulation.SetVirus(virus_Utilisateur);
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Veuillez remplir tous les cases !");
		}
	}	

	public class actionNomOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.getFocusOwner().transferFocus();
		}
	}
	
	public class actionContaminationOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.getFocusOwner().transferFocus();
		}
	}
	
	public class actionMortaliteOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.getFocusOwner().transferFocus();
		}
	}
	
	public class actionGuerisonOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.getFocusOwner().transferFocus();
		}
	}

	public class actionIncubationOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			actionOk();
		}
	}
	
	public class actionOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			actionOk();		
		}
	}

	public class actionAnnuler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
}
