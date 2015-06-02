package construcciones;

import juego.Jugador;

public abstract class EdificioRecolector {
	protected int recursosRecolectados;
	protected Jugador jugador;
	
	public EdificioRecolector(Jugador jugador) {
		this.recursosRecolectados = 0;
		this.jugador = jugador;
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
	
	public void recolectar(int cantidadRecolectada) {
		this.recursosRecolectados += cantidadRecolectada;
	}
	
	
}
