package algoCraft.unidades;

import algoCraft.Atacable;
import algoCraft.unidades.excepciones.AtaqueFueraDeRangoException;


public class Arma {
	
	private int danio;
	private double rango;
	public Arma(int danio, double rango) {
		this.danio = danio;
		this.rango = rango;
	}
	
	public void atacar(Atacable atacable, double distanciaEntreArmaYAtacable) {
		if (!(distanciaEntreArmaYAtacable <= rango))
			throw new AtaqueFueraDeRangoException();
		atacable.recibePuntosDeDanio(danio);
	}
	//Cuando tengamos rango aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public double getRango() {
		return this.rango;
	}
	//Cuando tengamos danio aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getDanio() {
		return this.danio;
	}
}