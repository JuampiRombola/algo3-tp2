package algoCraft.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import algoCraft.construcciones.Base;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.recursos.Mineral;
import algoCraft.recursos.Recurso;

public class Mapa {
	private HashMap<Posicion, Posicionable> elementos;
	private HashMap<Posicion, Recurso> gasVespeno;
	private HashMap<Posicion, Mineral> minerales;
	private HashMap<Posicion, Base> bases;
	private static Mapa mapa = new Mapa();
	private int alto = 500;
	private int ancho = 500;

	private Mapa() {
		this.elementos = new HashMap<Posicion, Posicionable>(alto * ancho);
		this.generarBases();
		this.generarRecursos();
	}
	
	public static Mapa getMapa() {
		return mapa;
	}

	private void generarBases() {
		this.bases = new HashMap<Posicion, Base>();
		int x = 15;
		int y = 15;
		// Se crean 4 bases, una en cada vertice del mapa.
		this.bases.put(new Posicion(x, y, true), new Base(x, y));
		this.bases.put(new Posicion(alto-x, ancho-y, true), new Base(alto-x, ancho-y));
		this.bases.put(new Posicion(alto-x, y, true), new Base(alto-x, y));
		this.bases.put(new Posicion(x, ancho-y, true), new Base(x, ancho-y));
		this.elementos.putAll(this.bases);
	}

	private void generarRecursos() {
		this.gasVespeno = new HashMap<Posicion, Recurso>();
		this.minerales = new HashMap<Posicion, Mineral>();
		Iterator<Base> it = this.bases.values().iterator();
		while (it.hasNext()) {
			Base base = it.next();
			this.generarGasVespenoEntornoALaBase(base);
			this.generarMineralesEntornoALaBase(base);
		}
	}
	
	private void generarGasVespenoEntornoALaBase(Base base) {
		int cantidadGasVespeno = 0;
		while (!(cantidadGasVespeno == 4)) {
			Posicion posicion = obtenerPosicionAleatoriaEntornoALaBase(base);
			if (this.posicionEstaOcupada(posicion)) continue;
			this.gasVespeno.put(posicion, new Mineral(posicion.getX(), posicion.getY()));
			this.elementos.put(posicion, new Mineral(posicion.getX(), posicion.getY()));
			cantidadGasVespeno++;
		}
	}
	
	private void generarMineralesEntornoALaBase(Base base) {
		int cantidadMinerales = 0;
		while (!(cantidadMinerales == 6)) {
			Posicion posicion = obtenerPosicionAleatoriaEntornoALaBase(base);
			if (this.posicionEstaOcupada(posicion)) continue;
			this.minerales.put(posicion, new Mineral(posicion.getX(), posicion.getY()));
			this.elementos.put(posicion, new Mineral(posicion.getX(), posicion.getY()));
			cantidadMinerales++;
		}
	}
	
	private Posicion obtenerPosicionAleatoriaEntornoALaBase(Base base) {
		Posicion posicionBase = base.getPosicion();
		int corrimiento = 10;
		int xBase = posicionBase.getX();
		int yBase = posicionBase.getY();
		int xRandom = (int)Math.floor(Math.random()*((xBase+corrimiento)-(xBase-corrimiento))+(xBase-corrimiento));
		int yRandom = (int)Math.floor(Math.random()*((yBase+corrimiento)-(yBase-corrimiento))+(yBase-corrimiento));
		//System.out.println(xRandom + "y" + yRandom);
		return (new Posicion(xRandom, yRandom, base.esTerrestre()));
	}
	
	private void validadPosicion(Posicion posicion) throws PosicionInvalidaException {
		int x = posicion.getX();
		int y = posicion.getY();
		if (x < 0 || x > alto || y < 0 || y > ancho)
			throw new PosicionInvalidaException();
	}
	
	private boolean posicionEstaOcupada(Posicion posicion) {
		return this.elementos.containsKey(posicion);
	}
	
	public void agregarUnidad(Posicionable unidad) throws PosicionInvalidaException, PosicionOcupadaException {
		Posicion posicion = unidad.getPosicion();
		this.validadPosicion(posicion);
		if (this.posicionEstaOcupada(posicion))
			throw new PosicionOcupadaException();
		this.elementos.put(posicion, unidad);
	}
	
	public Posicionable getUnidad(Posicion posicion) throws PosicionVaciaException {
		if (!this.posicionEstaOcupada(posicion))
			throw new PosicionVaciaException();
		return elementos.get(posicion);

	}
	
	public void moverUnidad(Posicionable unidad, int xDestino, int yDestino) throws PosicionInvalidaException {
		this.validadPosicion(new Posicion(xDestino, yDestino, unidad.esTerrestre()));
		if (!this.posicionEstaOcupada(new Posicion(xDestino, yDestino, unidad.esTerrestre()))) {
			this.removerUnidad(unidad);
			unidad.setPosicion(xDestino, yDestino);
			this.elementos.put(unidad.getPosicion(), unidad);
		}
	}
	
	public void removerUnidad(Posicionable unidad) {
		this.elementos.remove(unidad.getPosicion());
	}
	
	public Collection<Base> getBases() {
		return this.bases.values();
	}
	
	public boolean hayGasVespenoEn(Posicion posicion) {
		return this.gasVespeno.containsKey(posicion);
	}
	
	public boolean hayMineralEn(Posicion posicion) {
		return this.minerales.containsKey(posicion);
	}
	
	public int getCantidadMinerales() {
		return this.minerales.size();
	}
	
	public int getCantidadGasVespeno() {
		return this.gasVespeno.size();
	}
	
	public Posicion getPosicionVaciaCercana(Posicion posicion) {
		ArrayList<Posicion> posiciones = posicion.obtenerPosicionesAdyacentes();
		Posicion posicionBuscada = posiciones.remove(0);
		while(this.posicionEstaOcupada(posicionBuscada)) {
			posiciones.addAll(posicionBuscada.obtenerPosicionesAdyacentes());
			posicionBuscada = posiciones.remove(0);
		}
		return posicionBuscada;
	}
}