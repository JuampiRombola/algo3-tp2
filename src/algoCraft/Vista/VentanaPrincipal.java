package algoCraft.Vista;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaPrincipal implements Observer{
	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	private Frame marco;
	/**
	 * @wbp.nonvisual location=61,69
	 */
	private final JLabel lblHolaMundo = new JLabel("Hola Mundo");
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	public VentanaPrincipal(){
		marco = new Frame("AlgoCraft");
		marco.addWindowListener(new CloseListener());
		lblHolaMundo.setHorizontalAlignment(SwingConstants.CENTER);
		marco.add(lblHolaMundo);
		marco.setVisible(true);
	}
	
	public void update(Observable arg0, Object arg1) {
		
	}
}
