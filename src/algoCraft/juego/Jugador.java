package algoCraft.juego;

import java.util.ArrayList;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.CentroDeMineral;
import algoCraft.construcciones.DepositoDeSuministros;
import algoCraft.construcciones.Edificio;
import algoCraft.construcciones.EdificioConstructor;
import algoCraft.construcciones.Fabrica;
import algoCraft.construcciones.Refineria;
import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.excepciones.ElEdificioNoPuedeCrearLaUnidad;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.juego.excepciones.NoSePuedeConstruirLaUnidadPorSobrepoblacion;
import algoCraft.juego.excepciones.NoSeTienenLosRecursosSuficientes;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Jugador {
	static int cantidadInicialDeMineral = 200;
	static int cantidadInicialDeGasVespeno = 0;
	static int cantidadMaximaDePoblacion = 0;
	static int cantidadInicialDePoblacion = 0;
	
	private String nombre;
	private int mineral;
	private int gasVespeno;
	private ArrayList<Edificio> edificios;
	private ArrayList<Unidad> unidades;
	private int poblacion;
	private int poblacionMaxima;
	

	public Jugador(String nombre) {
		this.nombre = nombre;
		this.mineral = cantidadInicialDeMineral;
		this.gasVespeno = cantidadInicialDeGasVespeno;
		this.edificios = new ArrayList<Edificio>();
		this.unidades = new ArrayList<Unidad>();
		this.poblacion = cantidadInicialDePoblacion;
		this.poblacionMaxima = cantidadMaximaDePoblacion;
	}
	
	public int getGasVespeno() {
		return this.gasVespeno;
	}
	
	public int getMineral() {
		return this.mineral;
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
		return this.poblacion;
	}
	
	public int getPoblacionMaxima() {
		return this.poblacionMaxima;
	}
	
	public void sumarUnidadesDeMineral(int cantidadASumar) {
		this.mineral += cantidadASumar;
	}
	
	public void sumarUnidadesDeGasVespeno(int cantidadASumar) {
		this.gasVespeno += cantidadASumar;
	}
	
	private void restarRecursosGastados(int cantidaDeMineralGastada, int cantidadDeGasVespenoGastado) {
		this.sumarUnidadesDeMineral(-cantidaDeMineralGastada);
		this.sumarUnidadesDeGasVespeno(-cantidadDeGasVespenoGastado);
	}
	
	public void sumarPoblacionMaxima(int cantidadASumar) {
		this.poblacionMaxima += cantidadASumar;
	}
	
	public void sumarPoblacion(int cantidadASumar) {
		this.poblacion += cantidadASumar;
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidades.add(unidad);
	}
	
	private void agregarEdificio(Edificio edificio) {
		this.edificios.add(edificio);
	}
	
	private void verificarRecursos(int mineralesAGastar, int gasVespenoAGastar) throws NoSeTienenLosRecursosSuficientes {
		if ((mineralesAGastar > this.mineral) || (gasVespenoAGastar > this.gasVespeno))
			throw new NoSeTienenLosRecursosSuficientes();
	}

	
	private Edificio validarDependenciasEdificios(Class<?> claseDeEdificioQueDebePoseer) throws NoSePuedeConstruirElEdificio {
		for (Edificio edificio : this.edificios) {
			if ((edificio.getClass() == claseDeEdificioQueDebePoseer) && (!edificio.estaEnConstruccion()))
				return edificio;
		}
		throw new NoSePuedeConstruirElEdificio();
	}
	
	public void crearCentroDeMineral(Mineral mineral) throws NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {
		this.verificarRecursos(CentroDeMineral.cantidadMineral, CentroDeMineral.cantidadGasVespeno);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		this.agregarEdificio(centro);
		this.restarRecursosGastados(CentroDeMineral.cantidadMineral, CentroDeMineral.cantidadGasVespeno);
		Mapa.getMapa().agregarUnidad(centro);
	}
	
	public void crearDepositoDeSuministros(int x, int y) throws NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {
		this.verificarRecursos(DepositoDeSuministros.cantidadMineral, DepositoDeSuministros.cantidadGasVespeno);
		DepositoDeSuministros deposito = new DepositoDeSuministros(x, y);
		this.agregarEdificio(deposito);
		this.restarRecursosGastados(DepositoDeSuministros.cantidadMineral, DepositoDeSuministros.cantidadGasVespeno);
		Mapa.getMapa().agregarUnidad(deposito);
	}
	
	public void crearRefineria(GasVespeno gas) throws NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {
		this.verificarRecursos(Refineria.cantidadMineral, Refineria.cantidadGasVespeno);
		Refineria refineria = new Refineria(gas);
		this.agregarEdificio(refineria);
		this.restarRecursosGastados(Refineria.cantidadMineral, Refineria.cantidadGasVespeno);
		Mapa.getMapa().agregarUnidad(refineria);
	}
	
	public void crearBarraca(int x, int y) throws NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {	
		this.verificarRecursos(Barraca.cantidadMineral, Barraca.cantidadGasVespeno);
		Barraca barraca = new Barraca(x, y);
		this.agregarEdificio(barraca);
		this.restarRecursosGastados(Barraca.cantidadMineral, Barraca.cantidadGasVespeno);
		Mapa.getMapa().agregarUnidad(barraca);
	}
	
	public void crearFabrica(int x, int y) throws NoSePuedeConstruirElEdificio, NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {
		Edificio barraca = this.validarDependenciasEdificios(Barraca.class);
		this.verificarRecursos(Fabrica.cantidadMineral, Fabrica.cantidadGasVespeno);
		Fabrica fabrica = new Fabrica(x, y, (Barraca)barraca);
		this.agregarEdificio(fabrica);
		this.restarRecursosGastados(Fabrica.cantidadMineral, Fabrica.cantidadGasVespeno);
		Mapa.getMapa().agregarUnidad(fabrica);
	}
	
	private void sePuedeConstruirUnidad(int cantidadDePoblacionQueOcupa) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion {
		if (!(this.poblacion + cantidadDePoblacionQueOcupa <= this.poblacionMaxima))
			throw new NoSePuedeConstruirLaUnidadPorSobrepoblacion();
	}
	
	public void crearMarine(EdificioConstructor barraca) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion, ElEdificioNoPuedeCrearLaUnidad {
		this.sePuedeConstruirUnidad(Marine.cantidadDePoblacion);
		this.verificarRecursos(Marine.cantidadMineral, Marine.cantidadGasVespeno);
		barraca.crearMarine();
		this.restarRecursosGastados(Marine.cantidadMineral, Marine.cantidadGasVespeno);
	}
	
	public void crearGoliath(EdificioConstructor fabrica) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion, ElEdificioNoPuedeCrearLaUnidad {
		this.sePuedeConstruirUnidad(Goliath.cantidadDePoblacion);
		this.verificarRecursos(Goliath.cantidadMineral, Goliath.cantidadGasVespeno);
		fabrica.crearGoliath();
		this.restarRecursosGastados(Goliath.cantidadMineral, Goliath.cantidadGasVespeno);
	}

	public void avanzarTurno() {
		for (Edificio edificio : this.edificios)
			edificio.avanzarTurno(this);
		for (Unidad unidad : this.unidades)
			unidad.avanzarTurno();
	}
}
