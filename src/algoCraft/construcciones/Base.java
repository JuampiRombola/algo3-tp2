package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;

public class Base extends Edificio {
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 0;
	
	static int cantidadInicialDeMineral = 200;
	static int cantidadInicialDeGasVespeno = 0;
	static int cantidadMaximaDePoblacion = 0;
	static int cantidadInicialDePoblacion = 0;
	static int rangoDeConstruccionInicial = 5;
	
	private int mineral;
	private int gasVespeno;
	private int poblacion;
	private int poblacionMaxima;
	private int rangoConstruccion;
	
	public Base(int x, int y) {
		super(null, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse);
		this.estaEnConstruccion = false;
		this.mineral = cantidadInicialDeMineral;
		this.gasVespeno = cantidadInicialDeGasVespeno;
		this.poblacion = cantidadInicialDePoblacion;
		this.poblacionMaxima = cantidadMaximaDePoblacion;
		this.rangoConstruccion = rangoDeConstruccionInicial;
	}
	
	public int getRangoConstruccion() {
		return rangoConstruccion+jugador.getEdificios().size();
	}
	
	@Override
	public void recibePuntosDeDanio(int danio) {
		this.vida.recibirDanio(danio);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public int getGasVespeno() {
		return this.gasVespeno;
	}

	public int getMineral() {
		return this.mineral;
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
	
	public void sumarPoblacionMaxima(int cantidadASumar) {
		this.poblacionMaxima += cantidadASumar;
	}
	
	public void sumarPoblacion(int cantidadASumar) {
		this.poblacion += cantidadASumar;
	}
	
	public boolean hayCapacidadSuficiente(int cantidadAOcupar) {
		return (this.poblacion + cantidadAOcupar <= this.poblacionMaxima);
	}
	
	public boolean hayRecursosSuficientes(int mineralesAGastar, int gasVespenoAGastar) {
		return ((mineralesAGastar <= this.mineral) && (gasVespenoAGastar <= this.gasVespeno));
	}
}
