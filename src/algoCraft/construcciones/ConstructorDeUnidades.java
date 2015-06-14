package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Unidad;

public abstract class ConstructorDeUnidades extends Edificio {

	public ConstructorDeUnidades(int vida, Posicion posicion) {
		super(vida, posicion);
	}
	
	abstract void crearUnidad() throws EdificioNoHabilitadoException;
	
	abstract void avanzarTurno();
	
	abstract Unidad obtenerUltimaUnidadConstruida() throws NoSeCreoUnaNuevaUnidad;
}
