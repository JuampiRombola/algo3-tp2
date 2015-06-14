package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Barraca extends Edificio{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int turnosEnConstruirse = 12;
	private boolean estoyEnConstruccion;
	private boolean seCreoUnaUnidadNueva;
	private int contadorDeTurnos;
	private Queue<Unidad> unidadesEnConstruccion;
	private Unidad ultimaUnidadConstruida;
	

	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		estoyEnConstruccion = true;
		this.seCreoUnaUnidadNueva = false;
		this.contadorDeTurnos = 0;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
	}

	public void crearUnidad() throws ElEdificioEstaEnConstruccion {
		if (!this.estoyEnConstruccion) {
			Marine marine = new Marine(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(marine);
		} else {
			throw new ElEdificioEstaEnConstruccion();
		}
	}
	
	public boolean estaEnConstruccion() {
		return estoyEnConstruccion;
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
			this.contadorDeTurnos = 1;
		}
	}
	

	private void continuarCreandoLaUnidad() {
		Unidad unidad = this.unidadesEnConstruccion.peek();
		if (this.contadorDeTurnos == unidad.getTurnosEnConstruirse()) {
			unidad = this.unidadesEnConstruccion.poll();
			this.ultimaUnidadConstruida = unidad;
			this.seCreoUnaUnidadNueva = true;
			this.contadorDeTurnos = 1;
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
