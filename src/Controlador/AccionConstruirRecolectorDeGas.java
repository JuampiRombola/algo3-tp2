package Controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import algoCraft.juego.Juego;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.GasVespeno;

public class AccionConstruirRecolectorDeGas extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Juego juego;
	
	public AccionConstruirRecolectorDeGas(Juego juego, int x, int y){
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
		//Es feo castear. Pero estoy seguro que en esa posicion hay un gas.
		GasVespeno gas = (GasVespeno) Mapa.getMapa().getUnidad(posicion);
		jugadorActual.crearRefineria(gas);
	}
}
