package algoCraft.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import algoCraft.juego.Juego;

public class VentanaPrincipal implements Observer{
	
	private JFrame marco;
	private Juego juego;
	private PanelJugador jugadorFrame;
	private boolean primerTurno = true;
	
	public VentanaPrincipal(){
		marco = new JFrame("AlgoCraft");
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		marco.addWindowListener(new CloseListener());
		juego = new Juego();
		juego.addObserver(this);
		JMenuBar barraMenu = (new BarraMenu(marco, juego)).getBarraMenu();
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

	@Override//Se llama cuando se crea una partida o pasa un turno.
	public void update(Observable o, Object arg) {
		if(!primerTurno){
			marco.remove(jugadorFrame);
		}
		jugadorFrame = new PanelJugador(juego);
		marco.add(jugadorFrame);
		primerTurno = false;
	}
}
