package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class JugadorPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VistaRecursos vistaRecursos;
	JLabel nombreJugador;
	
	
	JugadorPanel(Juego juego){
		this.setBackground(Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		vistaRecursos = new VistaRecursos(juego);
		nombreJugador = new JLabel(juego.getJugadorActual().getNombre());
		nombreJugador.setForeground(Color.white);
		this.add(nombreJugador);
		this.add(vistaRecursos);
	}
}
