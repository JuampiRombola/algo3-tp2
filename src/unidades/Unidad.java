package unidades;

import interfacesParaUnidadesYEstructuras.Atacable;

public class Unidad implements Atacable{
int vidaActual;
	
	public Unidad(int vidaMaxima) {
		this.vidaActual = vidaMaxima;
	}

	public boolean estaDestruido(){
		return vidaActual <= 0;
	}
	public void recibePuntosDeDanio(int danio) {
		vidaActual = vidaActual - danio;
	}
}
