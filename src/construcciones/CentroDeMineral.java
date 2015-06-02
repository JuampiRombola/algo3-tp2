package construcciones;

import juego.Jugador;

public class CentroDeMineral extends EdificioRecolector{

	public CentroDeMineral(Jugador jugador) {
		super(jugador);
	}
	
	public void recolectar(int cantidadRecolectada) {
		super.recolectar(cantidadRecolectada);
		this.jugador.sumarMinerales(cantidadRecolectada);
	}
}
