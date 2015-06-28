package algoCraft.Vista;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algoCraft.juego.Juego;

public class PanelJugador extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PanelRecursos vistaRecursos;
	JLabel nombreJugador;
	
	
	PanelJugador(Juego juego){
		this.setBackground(Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		vistaRecursos = new PanelRecursos(juego);
		nombreJugador = new JLabel(juego.getJugadorActual().getNombre());
		nombreJugador.setForeground(Color.white);
		this.add(nombreJugador);
		this.add(vistaRecursos);
	}
}
