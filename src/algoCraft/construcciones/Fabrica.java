package algoCraft.construcciones;


import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
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
	
	public Fabrica(Jugador jugador, int x, int y, Barraca barraca) {
		super(jugador, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirGoliath);
		if (barraca.estaEnConstruccion())
			throw new NoSePuedeConstruirElEdificio();
		jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	}

	public void crearGoliath() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		this.jugador.pagarMineralGasVespeno(Goliath.getCantidadDeMineralQueCUesta(), Goliath.getCantidadDeGasQueCUesta());
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}
	
	@Override
	protected void crearLaUnidad() {
		Posicion posicionDelGoliath = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Goliath goliath = new Goliath(this.jugador, posicionDelGoliath.getX(), posicionDelGoliath.getY());
		this.jugador.agregarUnidad(goliath);
		this.jugador.sumarPoblacion(goliath.getPoblacionQueOcupa());
	}
}

