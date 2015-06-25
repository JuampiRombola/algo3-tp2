package algoCraft.construcciones;

import algoCraft.juego.Jugador;
import algoCraft.mapa.Posicion;
import algoCraft.recursos.Recurso;
import algoCraft.unidades.Vida;


public abstract class EdificioRecolector extends Edificio {
	protected int recursosRecolectados;
	protected Recurso recurso;
	protected Posicion posicion;
	protected Vida vida;
	
	public EdificioRecolector(Jugador jugador, Recurso recurso, int vida, int turnosEnConstruirse) {
		super(jugador, vida, recurso.getPosicion(), turnosEnConstruirse);
		this.recursosRecolectados = 0;
		this.recurso = recurso;
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
	
	public int recolectar(int cantidadARecolectar) {
		int cantidadRecolectada = this.recurso.extraer(cantidadARecolectar);
		this.recursosRecolectados += cantidadRecolectada;
		return cantidadRecolectada;
	}
}
