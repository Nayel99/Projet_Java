package projet_long_virus.Courbe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TraceurCourbes extends JFrame{

	public Courbe courbe;

	public TraceurCourbes(){
		super("Courbe");
		this.setSize(500, 500);

		this.courbe=new Courbe();

		this.getContentPane().add(this.courbe);

		this.setVisible(true);

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}

}