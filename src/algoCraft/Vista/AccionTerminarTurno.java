package algoCraft.Vista;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import algoCraft.juego.Juego;

public class AccionTerminarTurno extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private Juego juego;

	AccionTerminarTurno(Juego juego){
		super("Terminar turno");
		this.juego = juego;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		juego.siguienteJugador();
	}
	
}
