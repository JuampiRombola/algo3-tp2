package unidades;

import interfaces.unidadesYEstructuras.Atacable;
import interfaces.unidadesYEstructuras.Seleccionable;



public class Unidad implements Seleccionable{

protected int vidaMaxima;
protected int vidaActual;
protected Arma arma;

	public Unidad(int vidaMaxima, Arma arma){
		this.arma = arma;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
	}

	public void atacar(Atacable atacable, int distanciaEntreAtacanteYAtacable) {
		arma.atacar(atacable, distanciaEntreAtacanteYAtacable);
	}


	public boolean estaDestruido(){
		return vidaActual <= 0;
	}
	
	public int getVidaActual(){
		return vidaActual;
	}
	
	public int getVidaMaxima(){
		return vidaMaxima;
	}
	
	//Cuando tengamos danio aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public int getDanio(){
		return this.arma.getDanio();
	}
	//Cuando tengamos rango aereo y terrestre esto va a cambiar. Por eso no esta en las interfaces.
	//Tener en cuenta al usar.
	public double getRango(){
		return this.arma.getRango();
	}
	
	public void recibePuntosDeDanio(int danio) {
		vidaActual = vidaActual - danio;
		//Los valores negativos no tienen sentido en el modelo
		if(vidaActual < 0){ 
			vidaActual = 0;
		}
	}
}