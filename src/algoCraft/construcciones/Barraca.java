package algoCraft.construcciones;

import algoCraft.construcciones.excepciones.ElEdificioEstaEnConstruccion;
import algoCraft.juego.Jugador;
import algoCraft.juego.excepciones.ElEdificioPerteneceAOtroJugador;
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
	
	public static int getCostoMineral(){
		return cantidadMineral;
	}
	public static int getCostoGas(){
		return cantidadGasVespeno;
	}
	public Barraca(Jugador jugador, int x, int y) {
		super(jugador, vidaMaxima, new Posicion(x, y, inicialmenteTerrestre), turnosEnConstruirse, turnosEnProducirMarine);
		jugador.pagarMineralGasVespeno(cantidadMineral, cantidadGasVespeno);
	
	}

	public void crearMarine() throws ElEdificioEstaEnConstruccion {
		if (this.estaEnConstruccion)
			throw new ElEdificioEstaEnConstruccion();
		if (!this.jugador.estaActivo())
			throw new ElEdificioPerteneceAOtroJugador();
		this.jugador.pagarMineralGasVespeno(Marine.getCantidadDeMineralQueCUesta(), Marine.getCantidadDeGasQueCUesta());
		this.jugador.sumarPoblacion(Marine.getCantidadDePoblacionQueOcupa());
		cantidadDeUnidadesAProducir++;
		seTerminoDeCrearLaUnidad = false;
	}

	@Override
	protected void crearLaUnidad() {
		Posicion posicionDelMarine = Mapa.getMapa().getPosicionVaciaCercana(this.posicion);
		Marine marine = new Marine(this.jugador, posicionDelMarine.getX(), posicionDelMarine.getY());
		this.jugador.agregarUnidad(marine);
	}
	
	@Override
	protected void destruir() {
		super.destruir();
		if (this.cantidadDeUnidadesAProducir > 0) {
			for (int i = 0; i < this.cantidadDeUnidadesAProducir; i++)
				this.jugador.sumarPoblacion(-Marine.getCantidadDePoblacionQueOcupa());
		}
	}
}
