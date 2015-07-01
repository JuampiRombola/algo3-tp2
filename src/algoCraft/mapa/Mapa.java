package algoCraft.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import algoCraft.construcciones.Base;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;

public class Mapa extends Observable {
	private HashMap<Posicion, Posicionable> elementos;
	private static Mapa instancia = null;
	private int alto = 25;
	private int ancho = 25;
	
	private Mapa() {
		this.elementos = new HashMap<Posicion, Posicionable>(alto * ancho);
	}
	
	public void limpiarMapa() {
		elementos.clear();
	}
	
	public int getAlto() {
		return alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public static void reiniciarInstanciaParaTest() {
	    instancia = null;
	}
	
	public Collection<Posicionable> getElementos() {
		return (new ArrayList<Posicionable>(this.elementos.values()));
	}
	
	public static Mapa getMapa() {
		if (instancia == null)
			instancia = new Mapa();
		return instancia;
	}

	public Collection<Base> cargarBases(int cantidadJugadores) {
		// Maximo 4 jugadores, 1 base por jugador
		Collection<Base> bases = generarBases(cantidadJugadores);
		for (Base base : bases)
			this.elementos.put(base.getPosicion(), base);
		cargarRecursos(bases);
		setChanged();
		this.notifyObservers();
		return bases;
	}
	
	private Collection<Base> generarBases(int cantidadJugadores) {
		int x = 3;
		int y = 3;
		ArrayList<Base> bases = new ArrayList<Base> ();
		// Se crean 4 bases, una en cada vertice del mapa.
		bases.add(new Base(x, y));
		bases.add(new Base(alto-x+1, ancho-y+1));
		bases.add(new Base(alto-x+1, y));
		bases.add(new Base(x, ancho-y+1));
		int indiceFinal = (cantidadJugadores > 4) ? 4 : cantidadJugadores;
		return bases.subList(0, indiceFinal);
	}

	private void cargarRecursos(Collection<Base> bases) {
		Iterator<Base> it = bases.iterator();
		while (it.hasNext()) {
			Base base = it.next();
			this.generarRecursosEntornoALaBase(base);
		}
	}
	
	private void generarRecursosEntornoALaBase(Base base) {
		ArrayList<Posicion> posAdyacentes = base.getPosicion().obtenerPosicionesAdyacentes();
		Iterator<Posicion> it = posAdyacentes.iterator();
		int cantidadAgregada = 0;
		while (it.hasNext()) {
			Posicion posicion = it.next();
			if (cantidadAgregada < 2)
				this.agregarUnidad(new GasVespeno(posicion.getX()-1, posicion.getY()));
			else if (cantidadAgregada < 4)
				this.agregarUnidad(new Mineral(posicion.getX()-1, posicion.getY()));
			else
				this.agregarUnidad(new Mineral(posicion.getX()+1, posicion.getY()));
			cantidadAgregada++;
		}
	}
	
	private boolean esPosicionValida(Posicion posicion) {
		int x = posicion.getX();
		int y = posicion.getY();
		return ((x >= 1) && (x <= this.alto) && (y >= 1) && (y <= this.ancho));
	}
	
	public boolean posicionEstaOcupada(Posicion p) {
		return this.elementos.containsKey(p);
	}
	
	public void agregarUnidad(Posicionable unidad) throws PosicionInvalidaException, PosicionOcupadaException {
		Posicion posicion = unidad.getPosicion();
		if (!this.esPosicionValida(posicion))
			throw new PosicionInvalidaException();
		if (this.posicionEstaOcupada(posicion))
			throw new PosicionOcupadaException();
		this.elementos.put(posicion, unidad);
		this.setChanged();
		this.notifyObservers();
	}
	
	public Posicionable getPosicionable(Posicion posicion) throws PosicionVaciaException {
		if (!this.posicionEstaOcupada(posicion))
			throw new PosicionVaciaException();
		return elementos.get(posicion);
	}
	
	public void moverUnidad(Posicionable unidad, int xDestino, int yDestino) throws PosicionInvalidaException {
		if (!this.esPosicionValida(new Posicion(xDestino, yDestino, unidad.esTerrestre())))
			throw new PosicionInvalidaException();
		if (!this.posicionEstaOcupada(new Posicion(xDestino, yDestino, unidad.esTerrestre()))) {
			this.removerUnidad(unidad);
			unidad.setPosicion(xDestino, yDestino);
			this.elementos.put(unidad.getPosicion(), unidad);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void removerUnidad(Posicionable unidad) {
		this.elementos.remove(unidad.getPosicion());
		this.setChanged();
		this.notifyObservers();
	}
	
	public Posicion getPosicionVaciaCercana(Posicion posicion) {
		ArrayList<Posicion> posiciones = posicion.obtenerPosicionesAdyacentes();
		Posicion posicionBuscada = posiciones.remove(0);
		while(this.posicionEstaOcupada(posicionBuscada) || !this.esPosicionValida(posicionBuscada)) {
			posiciones.addAll(posicionBuscada.obtenerPosicionesAdyacentes());
			posicionBuscada = posiciones.remove(0);
		}
		
		return posicionBuscada;
	}

	public void reemplazarUnidad(Posicionable nuevoPosicionable) {
		if (this.posicionEstaOcupada(nuevoPosicionable.getPosicion()))
			this.removerUnidad(this.getPosicionable(nuevoPosicionable.getPosicion()));
		this.agregarUnidad(nuevoPosicionable);
		setChanged();
		this.notifyObservers();
	}
}