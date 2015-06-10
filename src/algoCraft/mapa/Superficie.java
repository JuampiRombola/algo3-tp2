package algoCraft.mapa;

import java.util.Collection;

import algoCraft.mapa.Posicion;

public class Superficie {
	
	private Collection<Posicion> posiciones;
	private Posicion posicionCentral;
	
	public Superficie(Collection<Posicion> posiciones, Posicion posicionCentral) {
		this.posiciones = posiciones;
		this.posicionCentral = posicionCentral;
	}
	
	public boolean contienePosicion(Posicion unaPosicion) {
		for (Posicion posicion : this.posiciones) {
			if (posicion.equals(unaPosicion))
				return true;
		}
		return false;
	}
	
	public void setPosicionCentral(Posicion posicion) {
		Posicion diferencia = posicion.diferencia(this.posicionCentral);
		this.transformarPosiciones(diferencia);
		this.posicionCentral = posicion;
	}

	private void transformarPosiciones(Posicion diferencia) {
		for (Posicion posicion : this.posiciones)
			posicion.sumar(diferencia);
	}
	
	public boolean colicionaConSuperficie(Superficie otraSuperficie) {
		for (Posicion posicion : otraSuperficie.posiciones) {
			if (this.contienePosicion(posicion))
				return true;
		}
		return false;
	}
	
	private double distanciaALaPosicion(Posicion unaPosicion) {
		double minimaDistancia = Double.POSITIVE_INFINITY;
		for (Posicion posicion : this.posiciones) {
			if (posicion.calcularDistancia(unaPosicion) < minimaDistancia)
				minimaDistancia = posicion.calcularDistancia(unaPosicion);
		}
		return minimaDistancia;
	}
	
	public double distanciaALaSuperficie(Superficie unaSuperficie) {
		double minimaDistancia = Double.POSITIVE_INFINITY;
		for (Posicion posicion : this.posiciones) {
			if (unaSuperficie.distanciaALaPosicion(posicion) < minimaDistancia)
				minimaDistancia = unaSuperficie.distanciaALaPosicion(posicion);
		}
		return minimaDistancia;
	}
}
