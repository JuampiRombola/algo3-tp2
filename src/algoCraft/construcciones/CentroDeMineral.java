package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.recursos.Mineral;

public class CentroDeMineral extends EdificioRecolector{
	static boolean esTerrestre = true;
	static int cantidadARecolectar = 10;
	static int vida = 500;
	static int turnosEnConstruirse = 4;
	public static int cantidadMineral = 50;
	public static int cantidadGasVespeno = 0;
	
	public CentroDeMineral(Mineral mineral) {
		super(mineral, vida, turnosEnConstruirse);
	}
	
	public void recolectar() {
		super.recolectar(cantidadARecolectar);
	}
		
	@Override
	public void avanzarTurno(Jugador jugador) {
		super.avanzarTurno(jugador);
		if (!this.estaEnConstruccion) {
			this.recolectar();
			jugador.sumarUnidadesDeMineral(cantidadARecolectar);
		}
	}
}
