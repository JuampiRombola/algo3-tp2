package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;

public class AccionConstruirBarraca extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	
	public AccionConstruirBarraca(Juego juego, int x, int y){
		super("Construir");
		this.x = x;
		this.y = y;
		this.juego = juego;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual;
		jugadorActual = juego.getJugadorActual();
		jugadorActual.crearBarraca(x, y);
	}
}
