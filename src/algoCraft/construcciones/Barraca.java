package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Marine;
import algoCraft.unidades.Unidad;

public class Barraca extends ConstructorDeUnidades{
	static int vidaMaxima = 1000;
	static boolean inicialmenteTerrestre = true;
	static int nivel = 1;
	static int turnosEnConstruirse = 12;
	private boolean habilitado;
	private int turnoActual;
	private Queue<Unidad> unidadesEnConstruccion;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		this.habilitado = false;
		this.turnoActual = 0;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
		this.vida.setVidaActualEnCero();
	}

	public Unidad crearUnidad() throws EdificioNoHabilitadoException {
		if (this.estaHabilitado()) {
			Marine marine = new Marine(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(marine);
			return marine;
		} else {
			throw new EdificioNoHabilitadoException();
		}
	}
	
	public int getNivel() {
		return nivel;
	}

	void habilitarProduccion() {
		this.habilitado = true;
	}

	void deshabilitarProduccion() {
		this.habilitado = false;
	}
	
	public boolean estaHabilitado() {
		return this.habilitado;
	}
	
	public void avanzarTurno() {
		this.construccionPorTurno();
		this.construirUnidades();
	}
	
	private void construccionPorTurno() {
		if (!this.estaHabilitado()) {
			if (this.turnoActual == turnosEnConstruirse)
				this.vida.maximizarVida();
				this.habilitarProduccion();
				this.turnoActual = 0;
			} else {
				this.vida.sumarVida(this.vida.getPuntosDeVidaMaximos() / turnosEnConstruirse);
				this.turnoActual += 1;
		}
	}
	
	private void construirUnidades() {
		if (!this.unidadesEnConstruccion.isEmpty()) {
			if (this.turnoActual == (this.unidadesEnConstruccion.peek()).getTurnosEnConstruirse()) {
				@SuppressWarnings("unused")
				Unidad unidad = this.unidadesEnConstruccion.poll();
				this.turnoActual = 0;
			}
			this.turnoActual += 1;
		}
	}
}
