package unidades;

import interfacesParaUnidadesYEstructuras.Atacable;

public class Unidad implements Atacable{
private int vidaActual;
private int vidaMaxima;
	
	public Unidad(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
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