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
		nombreJugador = new JLabel(tamanioLetra(16, "Jugador activo: "+juego.getJugadorActual().getNombre()));
		nombreJugador.setAlignmentX(LEFT_ALIGNMENT);
		nombreJugador.setForeground(Color.white);
		this.add(nombreJugador);
		this.add(vistaRecursos);
	}
	
	private String tamanioLetra(int tamanio, String string) {
		return ("<html><span style='font-size:"+tamanio+"px'>"+string+"</span></html>");
	}
}
