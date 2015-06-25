package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.recursos.GasVespeno;

public class Refineria extends EdificioRecolector {
	static boolean esTerrestre = true;
	static int cantidadARecolectar = 10;
	static int vida = 750;
	static int turnosEnConstruirse = 6;
	public static int cantidadMineral = 100;
	public static int cantidadGasVespeno = 0;
	
	public Refineria(Jugador jugador, GasVespeno gas) {
		super(jugador, gas, vida, turnosEnConstruirse);
		jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	}
	
	public void recolectar() {
		super.recolectar(cantidadARecolectar);
	}
		
	@Override
	public void avanzarTurno() {
		super.avanzarTurno();
		if (!this.estaEnConstruccion) {
			this.recolectar();
			this.jugador.sumarUnidadesDeGasVespeno(cantidadARecolectar);
		}
	}
}
