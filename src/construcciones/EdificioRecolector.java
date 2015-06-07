package construcciones;

import mapa.Posicion;
import mapa.Posicionable;
import recursos.Recurso;


public abstract class EdificioRecolector implements Posicionable{
	protected int recursosRecolectados;
	protected Recurso recurso;
	protected Posicion posicion;
	
	public EdificioRecolector(Recurso recurso) {
		this.recursosRecolectados = 0;
		this.recurso = recurso;
		this.posicion = recurso.getPosicion();
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
	
	public void recolectar(int cantidadRecolectada) {
		this.recursosRecolectados += cantidadRecolectada;
		this.recurso.extraer(cantidadRecolectada);
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
