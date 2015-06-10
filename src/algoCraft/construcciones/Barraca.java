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
	private boolean dependenciasValidas;
	private boolean enConstruccion;
	private boolean seCreoUnaUnidadNueva;
	private int turnoActual;
	private Queue<Unidad> unidadesEnConstruccion;
	private Unidad ultimaUnidadConstruida;
	
	public Barraca(int x, int y) {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		this.dependenciasValidas = true;
		this.enConstruccion = true;
		this.seCreoUnaUnidadNueva = false;
		this.turnoActual = 0;
		this.unidadesEnConstruccion = new LinkedList<Unidad>();
		this.vida.setVidaActualEnCero();
	}

	public void crearUnidad() throws EdificioNoHabilitadoException {
		if (this.estaHabilitado()) {
			Marine marine = new Marine(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(marine);
		} else {
			throw new EdificioNoHabilitadoException();
		}
	}
	
	public int getNivel() {
		return nivel;
	}

	public void setDependenciasValidas() {
		this.dependenciasValidas = true;
	}

	public void setDependenciasNoValidas() {
		this.dependenciasValidas = false;
	}
	
	public boolean estaHabilitado() {
		return this.dependenciasValidas && !this.enConstruccion;
	}
	
	public void avanzarTurno() {
		this.construccionPorTurno();
		this.construirUnidades();
	}
	
	private void construccionPorTurno() {
		if (this.enConstruccion) {
			if (this.turnoActual == turnosEnConstruirse)
				this.vida.maximizarVida();
				this.setDependenciasValidas();
				this.enConstruccion = false;
				this.turnoActual = 0;
			} else {
				this.vida.sumarVida(this.vida.getPuntosDeVidaMaximos() / turnosEnConstruirse);
				this.turnoActual += 1;
		}
	}
	
	private void construirUnidades() {
		if (!this.unidadesEnConstruccion.isEmpty()) {
			Unidad unidad = this.unidadesEnConstruccion.peek();
			if (this.turnoActual == unidad.getTurnosEnConstruirse()) {
				unidad = this.unidadesEnConstruccion.poll();
				this.ultimaUnidadConstruida = unidad;
				this.seCreoUnaUnidadNueva = true;
				this.turnoActual = 0;
			}
			this.turnoActual += 1;
		}
	}
	
	public boolean getSeCreoUnaUnidadNueva() {
		return this.seCreoUnaUnidadNueva;
	}
	
	public Unidad obtenerUltimaUnidadConstruida() throws NoSeCreoUnaNuevaUnidad {
		if (this.getSeCreoUnaUnidadNueva()) {
			return this.ultimaUnidadConstruida;
		} else {
			throw new NoSeCreoUnaNuevaUnidad();
		}
	}
}
