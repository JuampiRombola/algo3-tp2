package mapa;

import interfaces.unidadesYEstructuras.Seleccionable;

public class Casillero {
	private boolean estaVacioTierra;
	private Seleccionable terrestre;
	
	public Casillero() {
		this.estaVacioTierra = true;
	}
	
	public boolean estaVacioTierra() {
		return this.estaVacioTierra;
	}

	public void ocuparTierra(Seleccionable seleccionable) {
		this.terrestre = seleccionable;
		this.estaVacioTierra = false;
	}

	public Seleccionable obtenerTierra(){
		return this.terrestre;
	}
}