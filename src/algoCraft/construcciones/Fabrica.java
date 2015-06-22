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
	
	public Fabrica(int x, int y, Barraca barraca) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirGoliath);
		if(barraca.estaEnConstruccion())
			throw new NoSePuedeConstruirElEdificio();
	}

	public void crearGoliath() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}
	
	@Override
	protected void crearLaUnidad(Jugador jugador) {
		Posicion posicionDelGoliath = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Goliath goliath = new Goliath(posicionDelGoliath.getX(), posicionDelGoliath.getY());
		jugador.agregarUnidad(goliath);
		jugador.sumarPoblacion(goliath.getPoblacionQueOcupa());
	}
}

