package algoCraft.construcciones;

import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;

public class Barraca extends EdificioConstructor{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	public static int cantidadMineral = 150;
	public static int cantidadGasVespeno = 0;
	public static int turnosEnProducirMarine = 3;
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirMarine);
	}

	public void crearMarine() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}

	@Override
	protected void crearLaUnidad(Jugador jugador) {
		Posicion posicionDelMarine = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Marine marine = new Marine(posicionDelMarine.getX(), posicionDelMarine.getY());
		jugador.agregarUnidad(marine);
		jugador.sumarPoblacion(marine.getPoblacionQueOcupa());
	}
}
