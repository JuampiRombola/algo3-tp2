package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.recursos.Recurso;
import algoCraft.unidades.Vida;


public abstract class EdificioRecolector extends Edificio {
	protected int recursosRecolectados;
	protected Recurso recurso;
	protected Posicion posicion;
	protected Vida vida;
	
	public EdificioRecolector(Recurso recurso, int vida, int turnosEnConstruirse) {
		super(vida, recurso.getPosicion(), turnosEnConstruirse);
		this.recursosRecolectados = 0;
		this.recurso = recurso;
	}
	
	public int getRecursosRecolectados() {
		return recursosRecolectados;
	}
	
	public void recolectar(int cantidadRecolectada) {
		this.recursosRecolectados += cantidadRecolectada;
		this.recurso.extraer(cantidadRecolectada);
	}
}
