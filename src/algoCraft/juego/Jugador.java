package algoCraft.juego;

import java.util.ArrayList;
import java.util.Observable;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.Base;
import algoCraft.construcciones.CreadorDeBarraca;
import algoCraft.construcciones.CreadorDeCentroDeMineral;
import algoCraft.construcciones.CreadorDeDepositoDeSuministros;
import algoCraft.construcciones.CreadorDeEdificios;
import algoCraft.construcciones.CreadorDeFabrica;
import algoCraft.construcciones.CreadorDeGoliath;
import algoCraft.construcciones.CreadorDeMarine;
import algoCraft.construcciones.CreadorDeRefineria;
import algoCraft.construcciones.CreadorDeUnidades;
import algoCraft.construcciones.Edificio;
import algoCraft.construcciones.Fabrica;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.excepciones.ElJugadorNoEstaActivoException;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Jugador extends Observable {
	private String nombre;
	private ArrayList<Edificio> edificios;
	private ArrayList<Unidad> unidades;
	private Base base;
	private boolean estaActivo;
	
	public Jugador(String nombre, Base base) {
		this.edificios = new ArrayList<Edificio>();
		this.unidades = new ArrayList<Unidad>();
		this.nombre = nombre;
		this.base = base;
		this.estaActivo = false;
	}
	
	public boolean perdioLaPartida() {
		return this.base.estaDestruido();
	}

	public void activar() {
		this.estaActivo = true;
		notifyObservers();
	}
	
	public void desactivar() {
		this.estaActivo = false;
	}
	
	public int getGasVespeno() {
		return this.base.getGasVespeno();
	}
	
	public int getMineral() {
		return this.base.getMineral();
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public ArrayList<Unidad> getUnidades() {
		return this.unidades;
	}
	
	public ArrayList<Edificio> getEdificios() {
		return this.edificios;
	}
	
	public int getPoblacion() {
		return this.base.getPoblacion();
	}
	
	public int getPoblacionMaxima() {
		return this.base.getPoblacionMaxima();
	}
	
	public Base getBase() {
		return this.base;
	}
	
	public void sumarUnidadesDeMineral(int cantidadASumar) {
		this.base.sumarUnidadesDeMineral(cantidadASumar);
	}
	
	public void sumarUnidadesDeGasVespeno(int cantidadASumar) {
		this.base.sumarUnidadesDeGasVespeno(cantidadASumar);
	}
	
	private void restarRecursosGastados(int cantidaDeMineralGastada, int cantidadDeGasVespenoGastado) {
		this.sumarUnidadesDeMineral(-cantidaDeMineralGastada);
		this.sumarUnidadesDeGasVespeno(-cantidadDeGasVespenoGastado);
	}
	
	public void sumarPoblacionMaxima(int cantidadASumar) {
		this.base.sumarPoblacionMaxima(cantidadASumar);
	}
	
	public void sumarPoblacion(int cantidadASumar) {
		this.base.sumarPoblacion(cantidadASumar);
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidades.add(unidad);
	}
	
	private void agregarEdificio(Edificio edificio) {
		this.edificios.add(edificio);
	}
	
	public void removerEdificio(Edificio edificio) {
		this.edificios.remove(edificio);
	}
	
	public void removerUnidad(Unidad unidad) {
		this.unidades.remove(unidad);
	}

	public void pagarMineralGasVespeno(int cantidadMineral, int cantidadGasVespeno) throws NoSeTienenLosRecursosSuficientes{
		if (!this.base.hayRecursosSuficientes(cantidadMineral, cantidadGasVespeno))
			throw new NoSeTienenLosRecursosSuficientes();
		this.restarRecursosGastados(cantidadMineral, cantidadGasVespeno);
	}
	
	private Edificio getDependenciaEdificio(Class<?> claseDeEdificioQueDebePoseer) throws NoSePuedeConstruirElEdificio {
		for (Edificio edificio : this.edificios) {
			if ((edificio.getClass() == claseDeEdificioQueDebePoseer) && (!edificio.estaEnConstruccion()))
				return edificio;
		}
		throw new NoSePuedeConstruirElEdificio();
	}

	public void crearEdificio(int x, int y, CreadorDeEdificios creador) throws ElJugadorNoEstaActivoException, NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {	
		if (!this.estaActivo)
			throw new ElJugadorNoEstaActivoException();
		Edificio edificio = creador.crearEdificio(this, x, y);
		this.agregarEdificio(edificio);
	}
	
	public void crearCentroDeMineral(Mineral mineral) {
		Posicion posicion = mineral.getPosicion();
		this.crearEdificio(posicion.getX(), posicion.getY(), new CreadorDeCentroDeMineral(mineral));
	}
	
	public void crearDepositoDeSuministros(int x, int y) {
		this.crearEdificio(x, y, new CreadorDeDepositoDeSuministros());
	}
	
	public void crearRefineria(GasVespeno gas) {
		Posicion posicion = gas.getPosicion();
		this.crearEdificio(posicion.getX(), posicion.getY(), new CreadorDeRefineria(gas));
	}
	
	public void crearBarraca(int x, int y) {	
		this.crearEdificio(x, y, new CreadorDeBarraca());
	}
	
	public void crearFabrica(int x, int y) {
		Edificio barraca = this.getDependenciaEdificio(Barraca.class);
		this.crearEdificio(x, y, new CreadorDeFabrica((Barraca)barraca));
	}
	
	private boolean sePuedeConstruirUnidad(int cantidadDePoblacionQueOcupa) {
		return this.base.hayCapacidadSuficiente(cantidadDePoblacionQueOcupa);
	}
	
	private void crearUnidad(CreadorDeUnidades creador, int poblacionQueOcupa) throws ElJugadorNoEstaActivoException, NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion{
		if (!this.estaActivo)
			throw new ElJugadorNoEstaActivoException();
		if (!this.sePuedeConstruirUnidad(poblacionQueOcupa))
			throw new NoSePuedeConstruirLaUnidadPorSobrepoblacion();
		creador.crearUnidad();
	}
	
	public void crearMarine(Barraca barraca) {
		this.crearUnidad(new CreadorDeMarine(barraca), Marine.getCantidadDePoblacionQueOcupa());
	}
	
	public void crearGoliath(Fabrica fabrica) {
		this.crearUnidad(new CreadorDeGoliath(fabrica), Goliath.getCantidadDePoblacionQueOcupa());
	}
}
