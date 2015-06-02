package unidades;

import interfaces.unidadesYEstructuras.Atacable;
import interfaces.unidadesYEstructuras.Seleccionable;



public abstract class Unidad implements Seleccionable{

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
	
	public int getDanio(){
		return this.arma.getDanio();
	}
	
	public int getRango(){
		return this.arma.getRango();
	}
}