package algoCraft.mapa;

import java.util.HashMap;

import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;

public class Mapa {
	private int alto, ancho;
	private HashMap<Posicion, Posicionable> unidades;
	
	public Mapa(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.unidades = new HashMap<Posicion, Posicionable>(alto * ancho);
	}
	
	private void validadPosicion(Posicion posicion) throws PosicionInvalidaException {
		int x = posicion.getX();
		int y = posicion.getY();
		if (x < 0 || x > this.alto || y < 0 || y > this.ancho)
			throw new PosicionInvalidaException();
	}
	
	private boolean posicionEstaOcupada(Posicion posicion) {
		return unidades.containsKey(posicion);
	}
	
	public void agregarUnidad(Posicionable unidad) throws PosicionInvalidaException {
		Posicion posicion = unidad.getPosicion();
		this.validadPosicion(posicion);
		if (!this.posicionEstaOcupada(posicion))
			this.unidades.put(posicion, unidad);
	}
	
	public Posicionable getUnidad(Posicion posicion) throws PosicionVaciaException {
		if (!this.posicionEstaOcupada(posicion))
			throw new PosicionVaciaException();
		return unidades.get(posicion);

	}
	
	public void moverUnidad(Posicionable unidad, int xDestino, int yDestino) throws PosicionInvalidaException {
		this.validadPosicion(new Posicion(xDestino, yDestino, unidad.esTerrestre()));
		if (!this.posicionEstaOcupada(new Posicion(xDestino, yDestino, unidad.esTerrestre()))) {
			this.removerUnidad(unidad);
			unidad.setPosicion(xDestino, yDestino);
			this.unidades.put(unidad.getPosicion(), unidad);
		}
	}
	
	public void removerUnidad(Posicionable unidad) {
		this.unidades.remove(unidad.getPosicion());
	}
	
}