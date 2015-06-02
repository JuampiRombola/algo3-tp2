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
	//Cuando tengamos rango aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getRango(){
		return this.rango;
	}
	//Cuando tengamos danio aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getDanio(){
		return this.danio;
	}
}