package algoCraft.construcciones;

import java.util.LinkedList;
import java.util.Queue;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Goliath;
import algoCraft.unidades.Unidad;

public class Fabrica extends ConstructorDeUnidades {
	static int vidaMaxima = 1250;
	static boolean inicialmenteTerrestre = true;
	static int nivel = 2;
	static int turnosEnConstruirse = 12;
	private boolean habilitado;
	private int turnoActual;
	private Queue<Unidad> unidadesEnConstruccion;
	
	public Fabrica(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		this.habilitado = false;
		this.turnoActual = 0;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
		this.vida.setVidaActualEnCero();
	}

	public Unidad crearUnidad() throws EdificioNoHabilitadoException {
		if (this.habilitado) {
			return new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
		} else {
			throw new EdificioNoHabilitadoException();
		}
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void habilitarProduccion() {
		this.habilitado = true;
	}

	public void deshabilitarProduccion() {
		this.habilitado = false;
	}
	
	public boolean estaHabilitado() {
		return habilitado;
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

