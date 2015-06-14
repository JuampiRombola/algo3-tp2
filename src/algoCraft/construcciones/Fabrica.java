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
	private boolean enConstruccion;
	private boolean seCreoUnaUnidadNueva;
	private int turnoActual;
	private Queue<Unidad> unidadesEnConstruccion;
	private Unidad ultimaUnidadConstruida;
	
	public Fabrica(int x, int y, Barraca barraca) throws LaBarracaEstaDestruida {
		super(vidaMaxima, new Posicion(x, y, inicialmenteTerrestre));
		if(!barraca.estaDestruido()){
			this.enConstruccion = true;
			this.seCreoUnaUnidadNueva = false;
			this.turnoActual = 1;
			this.unidadesEnConstruccion = new LinkedList<Unidad>();
			this.vida.setVidaActualEnCero();
		}else{
			throw new LaBarracaEstaDestruida();
		}
	}

	public void crearUnidad() throws ElEdificioEstaEnConstruccion {
		if (!this.enConstruccion) {
			Goliath goliath = new Goliath(this.posicion.getX(), this.posicion.getY() + 1);
			this.unidadesEnConstruccion.offer(goliath);
		} else {
			throw new ElEdificioEstaEnConstruccion();
		}
	}
	
	public void avanzarTurno() {
		this.construccionPorTurno();
		this.construirUnidades();
	}
	
	private void construccionPorTurno() {
		if (this.enConstruccion) {
			if (this.turnoActual == turnosEnConstruirse) {
				this.vida.maximizarVida();
				this.enConstruccion = false;
				this.turnoActual = 1;
			} else {
				this.vida.sumarVida(this.vida.getPuntosDeVidaMaximos() / turnosEnConstruirse);
				this.turnoActual += 1;
			}
		}
	}
	
	private void construirUnidades() {
		if (!this.unidadesEnConstruccion.isEmpty()) {
			Unidad unidad = this.unidadesEnConstruccion.peek();
			if (this.turnoActual == unidad.getTurnosEnConstruirse()) {
				unidad = this.unidadesEnConstruccion.poll();
				this.ultimaUnidadConstruida = unidad;
				this.seCreoUnaUnidadNueva = true;
				this.turnoActual = 1;
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

