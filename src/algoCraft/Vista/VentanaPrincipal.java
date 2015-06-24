package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class VentanaPrincipal{
	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	
	private JFrame marco;

	public VentanaPrincipal(){
		marco = new JFrame("AlgoCraft");
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		JMenuBar barraMenu = (new BarraMenu(marco)).getBarraMenu();
		marco.setJMenuBar(barraMenu);
		VistaMapa vistaMapa = new VistaMapa();
		marco.add(vistaMapa, BorderLayout.EAST);
		marco.getContentPane().setBackground(Color.black);
		marco.setVisible(true);
	}

	public static class CloseListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {	
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}
