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
	
	public static int getCostoMineral(){
		return cantidadMineral;
	}
	
	public static int getCostoGas(){
		return cantidadGasVespeno;
	}
	
	public Refineria(Jugador jugador, GasVespeno gas) {
		super(jugador, gas, vida, turnosEnConstruirse, cantidadMineral, cantidadGasVespeno);
	}
	
	public int recolectar() {
		return super.recolectar(cantidadARecolectar);
	}
		
	@Override
	public void avanzarTurno() {
		super.avanzarTurno();
		if (!this.estaEnConstruccion) {
			int cantidadRecolectada = this.recolectar();
			this.jugador.sumarUnidadesDeGasVespeno(cantidadRecolectada);
		}
	}
}
