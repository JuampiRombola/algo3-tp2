package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Unidad;

public abstract class EdificioConstructor extends Edificio{
	protected boolean estaEnConstruccion;
	protected boolean seCreoUnaUnidadNueva;
	protected int contadorDeTurnos;
	protected int turnosEnConstruirse;
	protected Queue<Unidad> unidadesEnConstruccion;
	protected Unidad ultimaUnidadConstruida;

	
	public EdificioConstructor(int vida, Posicion posicion, int turnosEnConstruirse) {
		super(vida, posicion);
		this.estaEnConstruccion = true;
		this.seCreoUnaUnidadNueva = false;
		this.contadorDeTurnos = 0;
		this.turnosEnConstruirse = turnosEnConstruirse;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
	}

	public boolean estoyCreandoUnaUnidad() {
		return !unidadesEnConstruccion.isEmpty();
	}

	public void avanzarTurno() {
		this.contadorDeTurnos++;
		if(this.estaEnConstruccion){
			continuarMiConstruccion();
		}else
			if(estoyCreandoUnaUnidad()){
				continuarCreandoLaUnidad();
			}
	}

	public void continuarMiConstruccion() {
		if(this.contadorDeTurnos == this.turnosEnConstruirse){
			this.estaEnConstruccion = false;
			this.contadorDeTurnos = 0;
		}
	}
	
	public boolean estaEnConstruccion() {
		return this.estaEnConstruccion;
	}

	public void continuarCreandoLaUnidad() {
		Unidad unidad = this.unidadesEnConstruccion.peek();
		if (this.contadorDeTurnos == unidad.getTurnosEnConstruirse()) {
			unidad = this.unidadesEnConstruccion.poll();
			this.ultimaUnidadConstruida = unidad;
			this.seCreoUnaUnidadNueva = true;
			this.contadorDeTurnos = 0;
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

