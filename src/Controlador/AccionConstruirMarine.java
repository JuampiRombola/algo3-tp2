package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import algoCraft.construcciones.Barraca;
import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;

public class AccionConstruirMarine extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	
	public AccionConstruirMarine(Juego juego, int x, int y){
		super("Construir");
		this.x = x;
		this.y = y;
		this.juego = juego;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual;
		jugadorActual = juego.getJugadorActual();
		Posicion posicion = new Posicion(x, y, true);
		Barraca barraca = (Barraca) Mapa.getMapa().getUnidad(posicion);
		jugadorActual.crearMarine(barraca);
	}
}
