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
	static int cantidadMineral = 150;
	static int cantidadGasVespeno = 0;
	static int turnosEnProducirMarine = 3;
	
	public Barraca(Jugador jugador, int x, int y) {
		super(jugador, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirMarine);
		jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	
	}

	public void crearMarine() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		this.jugador.pagarMineralGasVespeno(Marine.getCantidadDeMineralQueCUesta(), Marine.getCantidadDeGasQueCUesta());
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}

	@Override
	protected void crearLaUnidad() {
		Posicion posicionDelMarine = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Marine marine = new Marine(this.jugador, posicionDelMarine.getX(), posicionDelMarine.getY());
		this.jugador.agregarUnidad(marine);
		this.jugador.sumarPoblacion(marine.getPoblacionQueOcupa());
	}
}
