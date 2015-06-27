package algoCraft.mapa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import algoCraft.construcciones.Base;
import algoCraft.construcciones.CentroDeMineral;
import algoCraft.construcciones.EdificioRecolector;
import algoCraft.construcciones.Refineria;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.mapa.excepciones.PosicionVaciaException;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;

public class Mapa extends Observable {
	private HashMap<Posicion, Posicionable> elementos;
	private HashMap<Posicion, GasVespeno> gasVespeno;
	private HashMap<Posicion, Mineral> minerales;
	private static Mapa instancia = null;
	private int alto = 25;
	private int ancho = 25;
	
	private Mapa() {
		this.elementos = new HashMap<Posicion, Posicionable>(alto * ancho);
		this.gasVespeno = new HashMap<Posicion, GasVespeno>();
		this.minerales = new HashMap<Posicion, Mineral>();
	}
	
	public void limpiarMapa() {
		elementos.clear();
		minerales.clear();
		gasVespeno.clear();
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
		cargarRecursos();
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
		bases.add(new Base(alto-x, ancho-y));
		bases.add(new Base(alto-x, y));
		bases.add(new Base(x, ancho-y));
		int indiceFinal = (cantidadJugadores > 4) ? 4 : cantidadJugadores;
		return bases.subList(0, indiceFinal);
	}

	private void cargarRecursos() {
		@SuppressWarnings("unchecked")
		Collection<Posicionable> bases = ((HashMap<Posicion, Posicionable>)this.elementos.clone()).values();
		Iterator<Posicionable> it = bases.iterator();
		while (it.hasNext()) {
			Posicionable base = it.next();
			this.generarGasVespenoEntornoALaBase(base);
			this.generarMineralesEntornoALaBase(base);
		}
	}
	
	private void generarGasVespenoEntornoALaBase(Posicionable base) {
		int cantidadGasVespeno = 0;
		while (!(cantidadGasVespeno == 2)) {
			Posicion posicion = obtenerPosicionAleatoriaEntornoALaBase(base);
			if (!posicion.esPositiva() || this.posicionEstaOcupada(posicion)) continue;
			GasVespeno gasVespeno = new GasVespeno(posicion.getX(), posicion.getY());
			this.gasVespeno.put(posicion, gasVespeno);
			cantidadGasVespeno++;
		}
	}
	
	private void generarMineralesEntornoALaBase(Posicionable base) {
		int cantidadMinerales = 0;
		while (!(cantidadMinerales == 6)) {
			Posicion posicion = obtenerPosicionAleatoriaEntornoALaBase(base);
			if (!posicion.esPositiva() || this.posicionEstaOcupada(posicion)) continue;
			Mineral mineral = new Mineral(posicion.getX(), posicion.getY());
			this.minerales.put(posicion, mineral);
			cantidadMinerales++;
		}
	}
	
	private Posicion obtenerPosicionAleatoriaEntornoALaBase(Posicionable base) {
		Posicion posicionBase = base.getPosicion();
		int corrimiento = 4;
		int xBase = posicionBase.getX();
		int yBase = posicionBase.getY();
		int xRandom = (int)Math.floor(Math.random()*((xBase+corrimiento)-(xBase-corrimiento))+(xBase-corrimiento));
		int yRandom = (int)Math.floor(Math.random()*((yBase+corrimiento)-(yBase-corrimiento))+(yBase-corrimiento));
		return (new Posicion(xRandom, yRandom, base.esTerrestre()));
	}
	
	private boolean esPosicionValida(Posicion posicion) {
		int x = posicion.getX();
		int y = posicion.getY();
		return ((x >= 0) && (x < this.alto) && (y >= 0) && (y < this.ancho));
	}
	
	public boolean posicionEstaOcupada(Posicion p) {
		return (this.elementos.containsKey(p) | this.minerales.containsKey(p) | this.gasVespeno.containsKey(p)) ;
	}
	
	public void agregarUnidad(Posicionable unidad) throws PosicionInvalidaException, PosicionOcupadaException {
		Posicion posicion = unidad.getPosicion();
		if (!this.esPosicionValida(posicion))
			throw new PosicionInvalidaException();
		if ((unidad.getClass() == Refineria.class) || (unidad.getClass() == CentroDeMineral.class)){
			this.ocuparRecurso((EdificioRecolector)unidad);
			return;
		}
		if (this.posicionEstaOcupada(posicion))
			throw new PosicionOcupadaException();
		this.elementos.put(posicion, unidad);
	}
	
	public Posicionable getUnidad(Posicion posicion) throws PosicionVaciaException {
		if (!this.posicionEstaOcupada(posicion))
			throw new PosicionVaciaException();
		if (!(this.hayGasVespenoEn(posicion) || this.hayMineralEn(posicion)))
			return elementos.get(posicion);
		if (this.hayGasVespenoEn(posicion))
			return gasVespeno.get(posicion);
		return minerales.get(posicion);
	}
	
	public void moverUnidad(Posicionable unidad, int xDestino, int yDestino) throws PosicionInvalidaException {
		if (!this.esPosicionValida(new Posicion(xDestino, yDestino, unidad.esTerrestre())))
			throw new PosicionInvalidaException();
		if (!this.posicionEstaOcupada(new Posicion(xDestino, yDestino, unidad.esTerrestre()))) {
			this.removerUnidad(unidad);
			unidad.setPosicion(xDestino, yDestino);
			this.elementos.put(unidad.getPosicion(), unidad);
		}
	}
	
	public void removerUnidad(Posicionable unidad) {
		this.elementos.remove(unidad.getPosicion());
	}
	
	public void removerRecurso(Posicionable recurso) {
		if (this.gasVespeno.containsKey(recurso.getPosicion()))
			gasVespeno.remove(recurso.getPosicion());
		if (this.minerales.containsKey(recurso.getPosicion()))
			minerales.remove(recurso.getPosicion());
	}
	
	public boolean hayGasVespenoEn(Posicion posicion) {
		return ((!this.elementos.containsKey(posicion)) && this.gasVespeno.containsKey(posicion));
	}
	
	public boolean hayMineralEn(Posicion posicion) {
		return ((!this.elementos.containsKey(posicion)) && this.minerales.containsKey(posicion));
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
	
	public void ocuparRecurso(EdificioRecolector recolector) {
		Posicion posicion = recolector.getPosicion();
		if (this.hayGasVespenoEn(posicion) || this.hayMineralEn(posicion))
			this.elementos.put(recolector.getPosicion(), recolector);
	}
}