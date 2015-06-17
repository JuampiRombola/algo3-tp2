package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Mapa;
import algoCraft.mapa.Posicion;
import algoCraft.mapa.excepciones.PosicionInvalidaException;
import algoCraft.mapa.excepciones.PosicionOcupadaException;
import algoCraft.unidades.Unidad;

public abstract class EdificioConstructor extends Edificio{
	protected boolean seCreoUnaUnidadNueva;
	protected Queue<Unidad> unidadesEnConstruccion;
	protected Unidad ultimaUnidadConstruida;

	
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
		if(estoyCreandoUnaUnidad()) {
			continuarCreandoLaUnidad(jugador);
		}
	}

	public void continuarCreandoLaUnidad(Jugador jugador) {
		Unidad unidad = this.unidadesEnConstruccion.peek();
		if (this.contadorDeTurnos == unidad.getTurnosEnConstruirse()) {
			unidad = this.unidadesEnConstruccion.poll();
			this.ultimaUnidadConstruida = unidad;
			this.seCreoUnaUnidadNueva = true;
			this.contadorDeTurnos = 0;
			
			Posicion posicionSalida = new Posicion(this.posicion.getX(), this.posicion.getY(), unidad.esTerrestre());
			Posicion posicionLibre = Mapa.getMapa().getPosicionVaciaCercana(posicionSalida);
			unidad.setPosicion(posicionLibre.getX(), posicionLibre.getY());
			try {
				Mapa.getMapa().agregarUnidad(unidad);
			} catch (PosicionInvalidaException e) {
			} catch (PosicionOcupadaException e) {}
			jugador.agregarUnidad(unidad);
			jugador.sumarPoblacion(unidad.getPoblacionQueOcupa());
			
		}
	}
	
	public boolean getSeCreoUnaUnidadNueva() {
		return this.seCreoUnaUnidadNueva;
	}
	
	public Unidad obtenerUltimaUnidadConstruida() throws NoSeCreoUnaNuevaUnidad {
		if (this.getSeCreoUnaUnidadNueva()) {
			this.seCreoUnaUnidadNueva = false;
			return this.ultimaUnidadConstruida;
		} else {
			throw new NoSeCreoUnaNuevaUnidad();
		}
	}
	
	public void crearMarine() throws ElEdificioNoPuedeCrearLaUnidad, ElEdificioEstaEnConstruccion {
		throw new ElEdificioNoPuedeCrearLaUnidad();
	}
	
	public void crearGoliath() throws ElEdificioNoPuedeCrearLaUnidad, ElEdificioEstaEnConstruccion {
		throw new ElEdificioNoPuedeCrearLaUnidad();
	}
}

