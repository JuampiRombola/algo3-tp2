package algoCraft.construcciones;

import algoCraft.mapa.Posicion;
import algoCraft.unidades.Unidad;

public abstract class ConstructorDeUnidades extends Edificio {

	public ConstructorDeUnidades(int vida, Posicion posicion) {
		super(vida, posicion);
	}
	
	abstract Unidad crearUnidad() throws EdificioNoHabilitadoException;
	
	abstract int getNivel();
	
	abstract void habilitarProduccion();
	
	abstract void deshabilitarProduccion();
	
	abstract boolean estaHabilitado();
}
