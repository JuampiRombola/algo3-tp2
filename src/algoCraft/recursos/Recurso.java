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
		return unidadesRestantes <= 0;
	}
	
	public int extraer(int cantidadExtraida) {
		if  (!this.estaDestruido()) {
			this.unidadesRestantes -= cantidadExtraida;
			return cantidadExtraida;
		}
		return 0;
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
}
