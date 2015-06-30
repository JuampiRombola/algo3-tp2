package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import algoCraft.Musica;
import algoCraft.juego.Juego;

public class VentanaPrincipal{
	
	private JFrame marco;
	private Juego juego;
	private PanelCentral panelCentral;
	private Musica musica;
	
	public VentanaPrincipal() throws Exception{
		marco = new JFrame("AlgoCraft");
		marco.setLayout(new BorderLayout());
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		juego = new Juego();
		musica = new Musica("recursos/Musica/main.wav");
	    musica.loop();
		JMenuBar barraMenu = (new BarraMenu(marco, juego, musica)).getBarraMenu();
		marco.setJMenuBar(barraMenu);
		marco.getContentPane().add(new PanelIzquierdo(juego), BorderLayout.WEST);
		panelCentral = new PanelCentral(juego);
		marco.getContentPane().add(panelCentral, BorderLayout.CENTER);
		marco.getContentPane().add(new VistaMapa(panelCentral), BorderLayout.EAST);
		marco.setVisible(true);
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
