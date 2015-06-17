package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.recursos.GasVespeno;

public class Refineria extends EdificioRecolector {
	static boolean esTerrestre = true;
	static int cantidadARecolectar = 10;
	static int vida = 750;
	static int turnosEnConstruirse = 6;
	
	public Refineria(GasVespeno gas) {
		super(gas, vida, turnosEnConstruirse);
	}
	
	public void recolectar() {
		super.recolectar(cantidadARecolectar);
	}
		
	@Override
	public void avanzarTurno(Jugador jugador) {
		super.avanzarTurno(jugador);
		if (!this.estaEnConstruccion) {
			this.recolectar();
			jugador.sumarUnidadesDeGasVespeno(cantidadARecolectar);
		}
	}
}
