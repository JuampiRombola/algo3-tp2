package algoCraft.construcciones;


import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ElEdificioPerteneceAOtroJugador;
import algoCraft.juego.excepciones.NoSePuedeConstruirElEdificio;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;

public class Fabrica extends EdificioConstructor {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	public static int cantidadMineral = 200;
	public static int cantidadGasVespeno = 100;
	public static int turnosEnProducirGoliath = 6;
	
	public static int getCostoMineral(){
		return cantidadMineral;
	}
	public static int getCostoGas(){
		return cantidadGasVespeno;
	}
	public Fabrica(Jugador jugador, int x, int y, Barraca barraca) {
		super(jugador, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirGoliath);
		if (barraca.estaEnConstruccion())
			throw new NoSePuedeConstruirElEdificio();
		if (!Mapa.getMapa().posicionEstaOcupada(new Posicion(x, y, inicialmenteTerrestre)))
			jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	}

	public void crearGoliath() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		if (!this.jugador.estaActivo())
			throw new ElEdificioPerteneceAOtroJugador();
		this.jugador.pagarMineralGasVespeno(Goliath.getCantidadDeMineralQueCUesta(), Goliath.getCantidadDeGasQueCUesta());
		this.jugador.sumarPoblacion(Goliath.getCantidadDePoblacionQueOcupa());
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}
	
	@Override
	protected void crearLaUnidad() {
		Posicion posicionDelGoliath = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Goliath goliath = new Goliath(this.jugador, posicionDelGoliath.getX(), posicionDelGoliath.getY());
		this.jugador.agregarUnidad(goliath);
	}
	
	@Override
	protected void destruir() {
		super.destruir();
		if (this.cantidadDeUnidadesAProducir > 0) {
			for (int i = 0; i < this.cantidadDeUnidadesAProducir; i++)
				this.jugador.sumarPoblacion(-Goliath.getCantidadDePoblacionQueOcupa());
		}
	}
}

