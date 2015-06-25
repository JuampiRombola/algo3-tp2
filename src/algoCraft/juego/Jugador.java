package algoCraft.juego;

import java.util.ArrayList;

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
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
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
	private Base base;
	private boolean estaActivo;
	

	public Jugador(String nombre, Base base) {
		this.nombre = nombre;
		this.mineral = cantidadInicialDeMineral;
		this.gasVespeno = cantidadInicialDeGasVespeno;
		this.edificios = new ArrayList<Edificio>();
		this.unidades = new ArrayList<Unidad>();
		this.poblacion = cantidadInicialDePoblacion;
		this.poblacionMaxima = cantidadMaximaDePoblacion;
		this.base = base;
		this.estaActivo = false;
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
	
	public Base getBase() {
		return this.base;
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

	
	public void pagarMineralGasVespeno(int cantidadMineral, int cantidadGasVespeno) throws NoSeTienenLosRecursosSuficientes{
		if (!seTienenLosRecursosSuficientes(cantidadMineral, cantidadGasVespeno))
			throw new NoSeTienenLosRecursosSuficientes();
		this.restarRecursosGastados(cantidadMineral, cantidadGasVespeno);
	}
	
	private boolean seTienenLosRecursosSuficientes(int mineralesAGastar, int gasVespenoAGastar) {
		if ((mineralesAGastar > this.mineral) || (gasVespenoAGastar > this.gasVespeno))
			return false;
		return true;
	}

	private Edificio validarDependenciasEdificios(Class<?> claseDeEdificioQueDebePoseer) throws NoSePuedeConstruirElEdificio {
		for (Edificio edificio : this.edificios) {
			if ((edificio.getClass() == claseDeEdificioQueDebePoseer) && (!edificio.estaEnConstruccion()))
				return edificio;
		}
		throw new NoSePuedeConstruirElEdificio();
	}

	public void crearEdificio(int x, int y, CreadorDeEdificios creador) throws ElJugadorNoEstaActivoException, NoSeTienenLosRecursosSuficientes, PosicionInvalidaException, PosicionOcupadaException {	
		this.validarSiEstaActivo();
		Edificio edificio = creador.crearEdificio(this, x, y);
		this.agregarEdificio(edificio);
		Mapa.getMapa().agregarUnidad(edificio);
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
		Edificio barraca = this.validarDependenciasEdificios(Barraca.class);
		this.crearEdificio(x, y, new CreadorDeFabrica((Barraca)barraca));
	}
	
	private boolean sePuedeConstruirUnidad(int cantidadDePoblacionQueOcupa) {
		if (!(this.poblacion + cantidadDePoblacionQueOcupa <= this.poblacionMaxima))
			return false;
		return true;
	}
	
	private void crearUnidad(CreadorDeUnidades creador, int poblacionQueOcupa) throws ElJugadorNoEstaActivoException, NoSePuedeConstruirLaUnidadPorSobrepoblacion, NoSeTienenLosRecursosSuficientes, ElEdificioEstaEnConstruccion{
		this.validarSiEstaActivo();
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

	public void avanzarTurno() {
		if (this.perdioLaPartida())
			return;
		for (Edificio edificio : this.edificios)
			edificio.avanzarTurno();
		for (Unidad unidad : this.unidades)
			unidad.avanzarTurno();
	}

	public boolean perdioLaPartida() {
		return this.base.estaDestruido();
	}
	
	public void validarSiEstaActivo() {
		if (!this.estaActivo)
			throw new ElJugadorNoEstaActivoException();
	}

	public void activar() {
		this.estaActivo = true;
	}
	
	public void desactivar() {
		this.estaActivo = false;
	}
	
	/*public void removerElementosDestruidos(ArrayList<Atacable> elementos) {
		for (int i = 0; i < elementos.size(); i++) {
			Atacable elemento = elementos.get(i);
			if (elemento.estaDestruido()) {
				elementos.remove(elemento);
				i--;
			}	
		}
	}*/
	
	public void actualizarEstado() {
		for (int i = 0; i < this.edificios.size(); i++) {
			Edificio edificio = this.edificios.get(i);
			if (edificio.estaDestruido()) {
				this.edificios.remove(edificio);
				i--;
			}
		}
		for (int i = 0; i < this.unidades.size(); i++) {
			Unidad unidad = this.unidades.get(i);
			if (unidad.estaDestruido()) {
				this.unidades.remove(unidad);
				i--;
			}
		}
	}
}
