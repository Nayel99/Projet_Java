package Graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class InterfaceChoixTemps extends JFrame {

	private static final long serialVersionUID = 6444375910808332245L;

	private JLabel JLTemps = new JLabel("Temps de simulation :");

	private JTextField MessageTemps = new JTextField();

	private final ActionListener actionTempsOK = new actionTempsOK();

	private  JButton BouttonOk = new JButton("Ok");


	private  JButton BouttonAnnuler = new JButton("Annuler");

	private final ActionListener actionAnnuler = new actionAnnuler();

	public InterfaceChoixTemps() {
		super( "Choisir le virus" );

		InitialiserFenetre();
		AjouterListener();
		this.add(CreerMessage(JLTemps, MessageTemps),BorderLayout.CENTER);
		this.add(sud(),BorderLayout.SOUTH);
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
	
	private void AjouterListener() {
		this.MessageTemps.addActionListener(this.actionTempsOK);

		this.BouttonOk.addActionListener(this.actionTempsOK);

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

	private void InitialiserFenetre() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize( 250, 150 );																			
		this.setLocationRelativeTo( null ); 
		this.setLayout( new BorderLayout() );
	}

	public void actionOk() {
		if (MessageTemps.getText().length() != 0) {
			InterfaceSimulation.InitialiserTemps(Integer.parseInt(MessageTemps.getText()));
			InterfacePrincipale.TempsExiste(true);
			InterfacePrincipale.Simulasionpossible();
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Veuillez remplir la case !");
		}
	}

	public class actionTempsOK implements ActionListener {
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

