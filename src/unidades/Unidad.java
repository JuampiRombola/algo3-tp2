package unidades;

import interfaces.unidadesYEstructuras.Atacable;
import interfaces.unidadesYEstructuras.Seleccionable;

public class Unidad implements Seleccionable{
private int vidaActual;
private int vidaMaxima;
private Arma arma;

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
	public void recibePuntosDeDanio(int danio) {
		vidaActual = vidaActual - danio;
		//Los valores negativos no tienen sentido en el modelo
		if(vidaActual < 0){ 
			vidaActual = 0;
		}
	}
	
	public int getVidaActual(){
		return vidaActual;
	}
	
	public int getVidaMaxima(){
		return vidaMaxima;
	}
}