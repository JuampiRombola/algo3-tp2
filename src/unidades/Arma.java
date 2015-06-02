package unidades;

import interfaces.unidadesYEstructuras.Atacable;

public class Arma {
	
	private int danio;
	private int rango;
	public Arma(int danio, int rango){
		this.danio = danio;
		this.rango = rango;
	}
	public void atacar(Atacable atacable, int distanciaEntreArmaYAtacable){
		if(distanciaEntreArmaYAtacable <= rango){
			atacable.recibePuntosDeDanio(danio);
		}
	}
}