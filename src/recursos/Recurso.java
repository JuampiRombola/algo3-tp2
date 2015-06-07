package recursos;

import mapa.Posicion;
import mapa.Posicionable;


public abstract class Recurso implements Posicionable{
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
	
	public void extraer(int cantidadExtraida) {
		if  (!this.estaDestruido()) {
			this.unidadesRestantes -= cantidadExtraida;
		}
	}
	
	public boolean esTerrestre(){
		return true;
	}
	
	public void setPosicion(int x, int y) {
		this.posicion = new Posicion(x, y, this.esTerrestre());
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
}
