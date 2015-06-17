package algoCraft.juego;

import java.util.ArrayList;

import algoCraft.construcciones.Barraca;
import algoCraft.construcciones.CentroDeMineral;
import algoCraft.construcciones.DepositoDeSuministros;
import algoCraft.construcciones.Edificio;
import algoCraft.construcciones.ElEdificioEstaEnConstruccion;
import algoCraft.construcciones.Fabrica;
import algoCraft.construcciones.Refineria;
import algoCraft.recursos.GasVespeno;
import algoCraft.recursos.Mineral;
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

	
	private void validarDependenciasEdificios(Edificio edificioQueDebePoseer) throws NoSePuedeConstruirElEdificio {
		for (Edificio edificio : this.edificios) {
			if (edificioQueDebePoseer.equals(edificio) && (!edificio.estaEnConstruccion())) {
				return;
			}
		}
		throw new NoSePuedeConstruirElEdificio();
	}
	
	public void crearCentroDeMineral(Mineral mineral) throws NoSeTienenLosRecursosSuficientes {
		int cantidadMineralCentroDeMineral = 50;
		int cantidadGasVespenoCentroDeMineral = 0;
		
		this.verificarRecursos(cantidadMineralCentroDeMineral, cantidadGasVespenoCentroDeMineral);
		CentroDeMineral centro = new CentroDeMineral(mineral);
		this.agregarEdificio(centro);
		this.restarRecursosGastados(cantidadMineralCentroDeMineral, cantidadGasVespenoCentroDeMineral);
	}
	
	public void crearDepositoDeSuministros(int x, int y) throws NoSeTienenLosRecursosSuficientes {
		int cantidadMineralDepositoDeSuministros = 100;
		int cantidadGasVespenoDepositoDeSuministros = 0;
		
		this.verificarRecursos(cantidadMineralDepositoDeSuministros, cantidadGasVespenoDepositoDeSuministros);
		DepositoDeSuministros deposito = new DepositoDeSuministros(x, y);
		this.agregarEdificio(deposito);
		this.restarRecursosGastados(cantidadMineralDepositoDeSuministros, cantidadGasVespenoDepositoDeSuministros);
	}
	
	public void crearRefineria(GasVespeno gas) throws NoSeTienenLosRecursosSuficientes {
		int cantidadMineralRefineria = 100;
		int cantidadGasVespenoRefineria = 0;
		
		this.verificarRecursos(cantidadMineralRefineria, cantidadGasVespenoRefineria);
		Refineria refineria = new Refineria(gas);
		this.agregarEdificio(refineria);
		this.restarRecursosGastados(cantidadMineralRefineria, cantidadGasVespenoRefineria);
	}
	
	public void crearBarraca(int x, int y) throws NoSeTienenLosRecursosSuficientes {
		int cantidadMineralBarraca = 150;
		int cantidadGasVespenoBarraca= 0;
		
		this.verificarRecursos(cantidadMineralBarraca, cantidadGasVespenoBarraca);
		Barraca barraca = new Barraca(x, y);
		this.agregarEdificio(barraca);
		this.restarRecursosGastados(cantidadMineralBarraca, cantidadGasVespenoBarraca);
	}
	
	public void crearFabrica(int x, int y) throws NoSePuedeConstruirElEdificio, NoSeTienenLosRecursosSuficientes {
		int cantidadMineralFabrica = 200;
		int cantidadGasVespenoFabrica = 100;
		
		this.validarDependenciasEdificios(new Barraca(0, 0));
		this.verificarRecursos(cantidadMineralFabrica, cantidadGasVespenoFabrica);
		Fabrica fabrica = new Fabrica(x, y);
		this.agregarEdificio(fabrica);
		this.restarRecursosGastados(cantidadMineralFabrica, cantidadGasVespenoFabrica);
	}
	
	private void sePuedeConstruirUnidad(int cantidadDePoblacionQueOcupa) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion {
		int poblacionActual = this.poblacion;
		if (poblacionActual + cantidadDePoblacionQueOcupa <= this.poblacionMaxima)
			return;
		throw new NoSePuedeConstruirLaUnidadPorSobrepoblacion();
	}
	
	public void crearMarine(Barraca barraca) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion {
		int cantidadMineralMarine = 50;
		int cantidadGasVespenoMarine = 0;
		int cantidadDePoblacionMarine = 1;
		
		this.sePuedeConstruirUnidad(cantidadDePoblacionMarine);
		this.verificarRecursos(cantidadMineralMarine, cantidadGasVespenoMarine);
		barraca.crearMarine();
		this.restarRecursosGastados(cantidadMineralMarine, cantidadGasVespenoMarine);
	}
	
	public void crearGoliath(Fabrica fabrica) throws NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion {
		int cantidadMineralGoliath = 100;
		int cantidadGasVespenoGoliath = 50;
		int cantidadDePoblacionGoliath = 2;
		
		this.sePuedeConstruirUnidad(cantidadDePoblacionGoliath);
		this.verificarRecursos(cantidadMineralGoliath, cantidadGasVespenoGoliath);
		fabrica.crearGoliath();
		this.restarRecursosGastados(cantidadMineralGoliath, cantidadGasVespenoGoliath);
	}

	public void avanzarTurno() {
		for (Edificio edificio : this.edificios) {
			edificio.avanzarTurno(this);
		}
		for (Unidad unidad : this.unidades) {
			unidad.avanzarTurno();
		}
	}
}
