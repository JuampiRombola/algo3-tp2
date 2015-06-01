package unidades;

import interfacesDelTrabajo.Atacable;

public class Unidad implements Atacable {
int vidaMaxima;
int vidaActual;
	
	Unidad(int vidaMaxima){
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
	}

	public boolean estaDestruida() {
		return (vidaActual < 0);
	}

	@Override
	public boolean aplicarDanio(int danio) {
		// TODO Auto-generated method stub
		return false;
	}

}
