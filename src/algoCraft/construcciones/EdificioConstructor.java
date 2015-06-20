package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.unidades.Unidad;

public abstract class EdificioConstructor extends Edificio {
	protected boolean seCreoUnaUnidadNueva;
	protected Queue<Unidad> unidadesEnConstruccion;

	
	public EdificioConstructor(int vida, Posicion posicion, int turnosEnConstruirse) {
		super(vida, posicion, turnosEnConstruirse);
		this.seCreoUnaUnidadNueva = false;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
	}

	public boolean estoyCreandoUnaUnidad() {
		return !unidadesEnConstruccion.isEmpty();
	}

	@Override
	public void avanzarTurno(Jugador jugador) {
		super.avanzarTurno(jugador);
		if(estoyCreandoUnaUnidad())
			continuarCreandoLaUnidad(jugador);
	}

	private void continuarCreandoLaUnidad(Jugador jugador) {
		Unidad unidad = this.unidadesEnConstruccion.peek();
		if (this.contadorDeTurnos == unidad.getTurnosEnConstruirse()) {
			unidad = this.unidadesEnConstruccion.poll();
			this.seCreoUnaUnidadNueva = true;
			this.contadorDeTurnos = 0;
			
			Posicion posicionSalida = new Posicion(this.posicion.getX(), this.posicion.getY(), unidad.esTerrestre());
			Posicion posicionLibre = Mapa.getMapa().getPosicionVaciaCercana(posicionSalida);
			unidad.setPosicion(posicionLibre.getX(), posicionLibre.getY());

			Mapa.getMapa().agregarUnidad(unidad);
			jugador.agregarUnidad(unidad);
			jugador.sumarPoblacion(unidad.getPoblacionQueOcupa());
		}
	}
	
	public boolean getSeCreoUnaUnidadNueva() {
		return this.seCreoUnaUnidadNueva;
	}
}

