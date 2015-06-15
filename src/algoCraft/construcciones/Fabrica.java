package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class Fabrica extends Edificio {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	private boolean estoyEnConstruccion;
	private boolean seCreoUnaUnidadNueva;
	private int contadorDeTurnos;
	private Queue<Unidad> unidadesEnConstruccion;
	private Unidad ultimaUnidadConstruida;
	
	public Fabrica(int x, int y, Barraca barraca) throws LaBarracaNoEsValida{
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		if(!barraca.estaDestruido() && !barraca.estaEnConstruccion()){
			estoyEnConstruccion = true;
			this.seCreoUnaUnidadNueva = false;
			this.contadorDeTurnos = 0;
			this.unidadesEnConstruccion = new LinkedList<Unidad>();
		}else{
			throw new LaBarracaNoEsValida();
		}
	}

	public void crearUnidad() throws ElEdificioEstaEnConstruccion {
		if (!this.estoyEnConstruccion) {
			Goliath goliath = new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(goliath);
		} else {
			throw new ElEdificioEstaEnConstruccion();
		}
	}
	
	public boolean estaHabilitado() {
		return !this.estoyEnConstruccion;
	}
	private boolean estoyCreandoUnaUnidad() {
		return !unidadesEnConstruccion.isEmpty();
	}
	

	public void avanzarTurno() {
		this.contadorDeTurnos++;
		if(estoyEnConstruccion){
			continuarMiConstruccion();
		}else
			if(estoyCreandoUnaUnidad()){
				continuarCreandoLaUnidad();
			}
	}

	private void continuarMiConstruccion() {
		if(contadorDeTurnos == turnosEnConstruirse){
			estoyEnConstruccion = false;
			contadorDeTurnos = 0;
		}
	}
	
	public boolean estaEnConstruccion() {
		return estoyEnConstruccion;
	}

	private void continuarCreandoLaUnidad() {
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
			seCreoUnaUnidadNueva = false;
			return this.ultimaUnidadConstruida;
		} else {
			throw new NoSeCreoUnaNuevaUnidad();
		}
	}
}

