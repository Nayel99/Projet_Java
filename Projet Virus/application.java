import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Graphic.InterfacePrincipale;

public class application {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
			InterfacePrincipale Mafenetre = new InterfacePrincipale();		
			Mafenetre.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
