package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;

public class Casillero {
	private Seleccionable elemento;
	private Posicion posicion;
	
	public Casillero(){
	}
	
	public Casillero(int x, int y) {
		this.posicion = new Posicion(x, y);
	}

	public boolean estaVacio() {
		return (this.elemento == null);
	}

	public void ocupar(Seleccionable seleccionable) {
		this.elemento = seleccionable;
	}
	
	public Seleccionable obtener() {
		return this.elemento;
	}

	public Seleccionable liberar() {
		Seleccionable aux = elemento;
		this.elemento = null;
		return aux;
	}

	public Posicion getPosicion() {
		return this.posicion;
	}
}