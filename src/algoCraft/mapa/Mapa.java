package algoCraft.mapa;

import java.util.ArrayList;
import java.util.Collection;

import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;

public class Mapa {
	private int alto, ancho;
	private Collection<Posicionable> unidades;
	
	public Mapa(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		this.unidades = new ArrayList<Posicionable>(alto * ancho);
	}
	
	private void validadPosicion(int x, int y) throws PosicionInvalidaException {
		if (x < 0 || x > this.alto || y < 0 || y > this.ancho)
			throw new PosicionInvalidaException();
	}
	
	private boolean posicionEstaOcupada(Posicion posicion) {
		for (Posicionable unidad : unidades) {
			if (posicion.equals(unidad.getPosicion()))
				return true;
		}
		return false;
	}
	
	public void agregarUnidad(Posicionable unidad, int x, int y) throws PosicionInvalidaException {
		this.validadPosicion(x, y);
		unidad.setPosicion(x, y);
		if (!this.posicionEstaOcupada(unidad.getPosicion()))
			this.unidades.add(unidad);
	}
	
	public Posicionable getUnidad(Posicion posicion) throws PosicionVaciaException {
		for (Posicionable unidad : unidades) {
			if (posicion.equals(unidad.getPosicion()))
				return unidad;
		}
		throw new PosicionVaciaException();

	}
	
	public void moverUnidad(Posicionable unidad, int xDestino, int yDestino) throws PosicionInvalidaException {
		this.validadPosicion(xDestino, yDestino);
		if (!this.posicionEstaOcupada(new Posicion(xDestino, yDestino, unidad.esTerrestre())))
			unidad.setPosicion(xDestino, yDestino);
	}
	
	public void removerUnidad(Posicionable unidad) {
		this.unidades.remove(unidad);
	}
	
}