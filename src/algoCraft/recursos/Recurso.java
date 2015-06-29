package algoCraft.recursos;

import algoCraft.mapa.Posicion;
import algoCraft.mapa.Posicionable;


public abstract class Recurso implements Posicionable {
	protected int unidadesRestantes;
	protected Posicion posicion;
	
	public Recurso(int unidadesMaximas, Posicion posicion) {
		this.unidadesRestantes = unidadesMaximas;
		this.posicion = posicion;
	}
	
	public int getUnidadesRestantes() {
		return this.unidadesRestantes;
	}
	
	public boolean estaDestruido() {
		return (this.unidadesRestantes <= 0);
	}
	
	public int extraer(int cantidadExtraida) {
		if  (this.estaDestruido())
			return 0;
		this.unidadesRestantes -= cantidadExtraida;
		return cantidadExtraida;
	}

	public boolean esTerrestre() {
		return true;
	}
	
	public void setPosicion(int x, int y) {
		this.posicion = new Posicion(x, y, this.esTerrestre());
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
	
	public void avanzarTurno() {	
	}
}
